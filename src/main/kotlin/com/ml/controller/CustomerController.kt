package com.ml.controller

import com.ml.controller.request.PostCustomerRequest
import com.ml.model.Customer
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/customers")
class CustomerController {
    var customers = mutableSetOf<Customer>();

    @GetMapping
    fun customer(): MutableSet<Customer> {
        return customers
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        customers.add(Customer(UUID.randomUUID().toString(), customer.name, customer.email))
        println(customer)
    }
}