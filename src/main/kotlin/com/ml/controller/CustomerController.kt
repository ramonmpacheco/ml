package com.ml.controller

import com.ml.controller.request.PostCustomerRequest
import com.ml.controller.request.PutCustomerRequest
import com.ml.model.Customer
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.function.Predicate

@RestController
@RequestMapping("/customers")
class CustomerController {
    var customers = mutableSetOf<Customer>();

    @GetMapping
    fun allCustomers(): MutableSet<Customer> {
        return customers
    }

    @GetMapping("/{id}")
    fun customer(@PathVariable id: String): Customer {
        return customers.first { c -> c.id == id }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        customers.add(Customer(UUID.randomUUID().toString(), customer.name, customer.email))
        println(customer)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: String, @RequestBody customer: PutCustomerRequest) {
        customers.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: String) {
        customers.removeIf { c -> c.id == id }
    }
}