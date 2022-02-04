package com.ml

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class MLApplication

fun main(args: Array<String>) {
	runApplication<MLApplication>(*args)
}
