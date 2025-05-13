package com.example.kotlin_spring_example.data.postgres.controller

import com.example.kotlin_spring_example.data.postgres.domain.PetDTO
import com.example.kotlin_spring_example.data.postgres.domain.PetSummary
import com.example.kotlin_spring_example.data.postgres.service.PetService
import org.springframework.http.HttpStatus.OK
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/postgres/pet")
class PetController (
    val petService: PetService
) {

    @GetMapping("/{id}")
    fun find(@PathVariable("id") id : Long): ResponseEntity<PetDTO> {
        val petDTO : PetDTO? = petService.find(id)

        if (petDTO == null) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity(petDTO, OK);
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<PetDTO>> {
        return ResponseEntity(petService.findAll(), OK);
    }

    @GetMapping("/type/{type}")
    fun findByType(@PathVariable("type") type : String): ResponseEntity<List<PetDTO>> {
        return ResponseEntity(petService.findByType(type), OK);
    }

    @GetMapping("/summary")
    fun findSummary(): ResponseEntity<PetSummary> {
        return ResponseEntity(petService.findSummary(), OK);
    }

    @PostMapping
    fun save(@RequestBody petDTO : PetDTO): ResponseEntity<String> {
        petService.save(petDTO)
        return ResponseEntity("Pet ${petDTO.name} Created!", CREATED)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody petDTO : PetDTO, @PathVariable("id") id : Long): ResponseEntity<String> {
        val updated : PetDTO? = petService.update(id, petDTO)

        if (updated == null) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity("Pet ${petDTO.name} Updated!", OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id : Long): ResponseEntity<String> {
        petService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
