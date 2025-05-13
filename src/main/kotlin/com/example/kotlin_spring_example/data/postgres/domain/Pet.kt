package com.example.kotlin_spring_example.data.postgres.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class Pet (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var size: Size?,
    var name: String,
    var type: String,
    var weight: BigDecimal,
    var createdAt: LocalDateTime?,
    var lastUpdated: LocalDateTime?,
) {

    constructor() : this(0, null, "", "", BigDecimal.ZERO, null, null)
}