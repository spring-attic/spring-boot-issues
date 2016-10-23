package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class GH7210Application

fun main(args: Array<String>) {
    SpringApplication.run(GH7210Application::class.java, *args)
}
