package com.example.kotlin_spring_example.data.mongodb.service

import com.example.kotlin_spring_example.data.mongodb.domain.Product
import com.example.kotlin_spring_example.data.mongodb.domain.ProductDTO
import com.example.kotlin_spring_example.data.mongodb.domain.Stock
import com.example.kotlin_spring_example.data.mongodb.repository.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService(
    val productRepository : ProductRepository
) {

    fun find(code : Long): ProductDTO? {
        val product : Product? = productRepository.findById(code)
        return product?.let(this::toProductDTO)
    }

    fun findAll(): List<ProductDTO> {
        return productRepository.findAll().map(this::toProductDTO)
    }

    fun findByType(type: String): List<ProductDTO> {
        return productRepository.findByType(type).map(this::toProductDTO)
    }

    fun findStock() : Stock {
        return productRepository.findStock()
    }

    fun save(productDTO : ProductDTO) {
        val product : Product = toProduct(productDTO)
        productRepository.save(product)
    }

    fun add(code : Long, value: Int): ProductDTO? {
        val product : Product? = productRepository.findById(code)

        return product?.let {
            product.quantity += value
            productRepository.save(product)
            toProductDTO(product)
        }
    }

    fun update(code : Long, productDTO : ProductDTO): ProductDTO? {
        val product : Product? = productRepository.findById(code)

        return product?.let {
            product.name = productDTO.name
            product.type = productDTO.type
            product.value = productDTO.value
            product.quantity = productDTO.quantity
            product.lastUpdated = LocalDateTime.now()

            productRepository.save(product)
            toProductDTO(product)
        }
    }

    fun delete(code : Long) {
        productRepository.deleteById(code)
    }

    private fun toProduct(productDTO: ProductDTO) : Product {
        val now : LocalDateTime = LocalDateTime.now()
        return Product(
            productDTO.code,
            productDTO.name,
            productDTO.type,
            productDTO.value,
            productDTO.quantity,
            now,
            now
        )
    }

    private fun toProductDTO(product: Product) : ProductDTO {
        return ProductDTO(
            product.code,
            product.name,
            product.type,
            product.value,
            product.quantity,
        )
    }
}