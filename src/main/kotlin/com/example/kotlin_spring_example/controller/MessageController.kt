package com.example.kotlin_spring_example.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController {

    @GetMapping
    fun send(@RequestParam("message") message : String) = "Message: $message"

    @GetMapping("/print")
    fun print(@RequestParam("message") message : String): String {
        println(message)
        return "Message printed!"
    }
}