package com.example.kotlin_spring_example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringExampleApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringExampleApplication>(*args)
}
