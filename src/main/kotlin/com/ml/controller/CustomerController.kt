package com.ml.controller

import com.ml.controller.request.PostCustomerRequest
import com.ml.model.Customer
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController {
    @GetMapping
    fun customer(): Customer {
        return Customer("123", "José", "jose@teste.com");
    }

    @PostMapping
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        println(customer);
    }
}