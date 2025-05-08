package com.example.kotlin_spring_example.data.mongodb.controller

import com.example.kotlin_spring_example.data.mongodb.domain.ProductDTO
import com.example.kotlin_spring_example.data.mongodb.domain.Stock
import com.example.kotlin_spring_example.data.mongodb.service.ProductService
import org.springframework.http.HttpStatus.OK
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/mongo/product")
class ProductControler (
    val productService: ProductService
) {

    @GetMapping("/{code}")
    fun find(@PathVariable("code") code : Long): ResponseEntity<ProductDTO> {
        val productDTO : ProductDTO? = productService.find(code)

        if (productDTO == null) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity(productDTO, OK);
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<ProductDTO>> {
        return ResponseEntity(productService.findAll(), OK);
    }

    @GetMapping("/type/{type}")
    fun findByType(@PathVariable("type") type : String): ResponseEntity<List<ProductDTO>> {
        return ResponseEntity(productService.findByType(type), OK);
    }

    @GetMapping("/stock")
    fun findStock(): ResponseEntity<Stock> {
        return ResponseEntity(productService.findStock(), OK);
    }

    @PostMapping
    fun save(@RequestBody productDTO : ProductDTO): ResponseEntity<String> {
        productService.save(productDTO)
        return ResponseEntity("Product ${productDTO.name} Created!", CREATED)
    }

    @PostMapping("/add")
    fun add(@RequestParam code : Long, @RequestParam value: Int): ResponseEntity<String> {
        val productDTO : ProductDTO? = productService.add(code, value)

        if (productDTO == null) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity("Added $value units to Product of code $code!", OK)
    }

    @PutMapping("/{code}")
    fun update(@RequestBody productDTO : ProductDTO, @PathVariable("code") code : Long): ResponseEntity<String> {
        val updated : ProductDTO? = productService.update(code, productDTO)

        if (updated == null) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity("Product ${productDTO.name} Updated!", OK)
    }

    @DeleteMapping("/{code}")
    fun delete(@PathVariable("code") code : Long): ResponseEntity<String> {
        productService.delete(code)
        return ResponseEntity.noContent().build()
    }
}
