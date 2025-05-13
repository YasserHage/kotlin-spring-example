package com.example.kotlin_spring_example.data.postgres.repository

import com.example.kotlin_spring_example.data.postgres.domain.Pet
import com.example.kotlin_spring_example.data.postgres.domain.PetSummary
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : JpaRepository<Pet, Long> {

    @Query("SELECT p FROM Pet p WHERE p.type = :type")
    fun findByType(type: String) : List<Pet>

    @Query("""
        SELECT 
            SUM(pet_type.quantity::int) as total,
            json_agg(json_build_object(
                  'type', pet_type.type,
                  'quantity', pet_type.quantity,
                  'pets', pet_type.pets)) as pets
        FROM 
            (SELECT p.type, COUNT(*) as quantity, json_agg(p) as pets FROM Pet p GROUP BY p.type) pet_type 
    """, nativeQuery = true)
    fun findSummary() : PetSummary
}