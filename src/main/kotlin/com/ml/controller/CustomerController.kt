package com.ml.controller

import com.ml.model.Customer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController {
    @GetMapping
    fun customer(): Customer {
        return Customer("123", "José", "jose@teste.com");
    }
}