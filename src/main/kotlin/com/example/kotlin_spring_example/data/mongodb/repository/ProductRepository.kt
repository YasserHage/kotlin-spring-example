package com.example.kotlin_spring_example.data.mongodb.repository

import com.example.kotlin_spring_example.data.mongodb.domain.Product
import com.example.kotlin_spring_example.data.mongodb.domain.ProductDTO
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.stereotype.Repository

@Repository
class ProductRepository (
    val mongoTemplate: MongoTemplate
) {

    fun findById(code : Long): Product? {
        return mongoTemplate.findById(code, Product::class.java)
    }

    fun save(product : Product) {
        mongoTemplate.save(product)
    }

    fun deleteById(code : Long) {
        mongoTemplate.remove(
            Query.query(where("code").`is`(code)),
            Product::class.java)
    }
}