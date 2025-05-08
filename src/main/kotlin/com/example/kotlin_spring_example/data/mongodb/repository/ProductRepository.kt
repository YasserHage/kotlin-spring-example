package com.example.kotlin_spring_example.data.mongodb.repository

import com.example.kotlin_spring_example.data.mongodb.domain.Product
import com.example.kotlin_spring_example.data.mongodb.domain.Stock
import org.bson.Document
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation.addField
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.Aggregation.group
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class ProductRepository (
    val mongoTemplate: MongoTemplate
) {

    fun findById(code : Long): Product? {
        return mongoTemplate.findById(code, Product::class.java)
    }

    fun findAll(): List<Product> {
        return mongoTemplate.findAll( Product::class.java)
    }

    fun findByType(type: String): List<Product> {
            return mongoTemplate.find(
                Query.query(where("type").`is`(type)),
                Product::class.java)
        }

    fun findStock(): Stock {
        val aggregation : Aggregation = Aggregation.newAggregation(
            addField("totalItemValue")
                .withValue(Document("\$multiply", listOf("\$quantity", "\$value")))
                .build(),
            group("type")
                .sum("totalItemValue").`as`("totalValue")
                .first("type").`as`("type")
                .push(Document("code", "\$_id")
                    .append("name", "\$name")
                    .append("type", "\$type")
                    .append("value", "\$value")
                    .append("quantity", "\$quantity")
                ).`as`("items"),
            group()
                .sum("totalValue").`as`("valueInStock")
                .push("\$\$ROOT").`as`("stock"),
        )

        return mongoTemplate.aggregate(aggregation, "product", Stock::class.java)
            .mappedResults[0]
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