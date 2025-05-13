package com.example.kotlin_spring_example.data.postgres.service

import com.example.kotlin_spring_example.data.postgres.domain.Pet
import com.example.kotlin_spring_example.data.postgres.domain.PetDTO
import com.example.kotlin_spring_example.data.postgres.domain.PetSummary
import com.example.kotlin_spring_example.data.postgres.repository.PetRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PetService(
    val petRepository : PetRepository
) {

    fun find(id : Long): PetDTO? {
        val pet : Pet? = petRepository.findByIdOrNull(id)
        return pet?.let(this::toPetDTO)
    }

    fun findAll(): List<PetDTO> {
        return petRepository.findAll().map(this::toPetDTO)
    }

    fun findByType(type: String): List<PetDTO> {
        return petRepository.findByType(type).map(this::toPetDTO)
    }

    fun findSummary() : PetSummary {
        return petRepository.findSummary()
    }

    fun save(petDTO : PetDTO) {
        val pet : Pet = toPet(petDTO)
        petRepository.save(pet)
    }

    fun update(id : Long, petDTO : PetDTO): PetDTO? {
        val pet : Pet? = petRepository.findByIdOrNull(id)

        return pet?.let {
            pet.size = petDTO.size
            pet.name = petDTO.name
            pet.type = petDTO.type
            pet.weight = petDTO.weight
            pet.lastUpdated = LocalDateTime.now()

            petRepository.save(pet)
            toPetDTO(pet)
        }
    }

    fun delete(id : Long) {
        petRepository.deleteById(id)
    }

    private fun toPet(petDTO: PetDTO) : Pet {
        val now : LocalDateTime = LocalDateTime.now()
        return Pet(
            petDTO.id,
            petDTO.size,
            petDTO.name,
            petDTO.type,
            petDTO.weight,
            now,
            now
        )
    }

    private fun toPetDTO(pet: Pet) : PetDTO {
        return PetDTO(
            pet.id,
            pet.size,
            pet.name,
            pet.type,
            pet.weight,
        )
    }
}