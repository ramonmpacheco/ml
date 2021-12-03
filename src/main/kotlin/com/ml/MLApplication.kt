package com.ml

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MLApplication

fun main(args: Array<String>) {
	runApplication<MLApplication>(*args)
}
