package com.example.kotlin_spring_example.data.mongodb.domain

import java.math.BigDecimal
import java.time.LocalDateTime

class ProductDTO(
    var code: Long,
    var name: String,
    var type: String,
    var value: BigDecimal,
    var quantity: Int,
) {
}