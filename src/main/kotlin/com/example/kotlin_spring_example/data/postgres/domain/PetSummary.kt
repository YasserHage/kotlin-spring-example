package com.example.kotlin_spring_example.data.postgres.domain

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class PetSummary(
    val total : Long,
    petsJson: String
) {
    val petsByType: List<PetType> by lazy {
        objectMapper.readValue(petsJson, object : TypeReference<List<PetType>>() {})
    }

    companion object {
        private val objectMapper = ObjectMapper().registerKotlinModule()
    }

    class PetType (
        val quantity : Long,
        val type: String,
        val pets : List<PetDTO>
    )
}

