package com.example.kotlin_spring_example.data.postgres.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
class PetDTO(
    var id: Long,
    var size: Size?,
    var name: String,
    var type: String,
    var weight: BigDecimal,
) {
}