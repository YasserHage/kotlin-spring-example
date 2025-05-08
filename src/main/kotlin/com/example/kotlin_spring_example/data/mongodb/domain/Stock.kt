package com.example.kotlin_spring_example.data.mongodb.domain

import java.math.BigDecimal

class Stock(
    val valueInStock : BigDecimal,
    val stock : List<StockType>
)

class StockType (
    val totalValue : BigDecimal,
    val type: String,
    val items : List<ProductDTO>
)