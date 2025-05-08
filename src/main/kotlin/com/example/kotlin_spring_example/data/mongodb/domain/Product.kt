package com.example.kotlin_spring_example.data.mongodb.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.math.BigDecimal
import java.time.LocalDateTime

@Document("product")
class Product (
    @Id
    var code: Long,
    var name: String,
    var type: String,
    @Field(targetType = FieldType.DECIMAL128)
    var value: BigDecimal,
    var quantity: Int,
    var createdAt: LocalDateTime,
    var lastUpdated: LocalDateTime,
) {
}