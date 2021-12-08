package com.ml.controller

import com.ml.controller.request.PostCustomerRequest
import com.ml.controller.request.PutCustomerRequest
import com.ml.model.Customer
import com.ml.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.function.Predicate

@RestController
@RequestMapping("/customers")
class CustomerController(val service: CustomerService) {

    @GetMapping
    fun allCustomers(@RequestParam name: String?): List<Customer> {
        return service.allCustomers(name)
    }

    @GetMapping("/{id}")
    fun customer(@PathVariable id: String): Customer {
        return service.customer(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        service.createCustomer(customer)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: String, @RequestBody customer: PutCustomerRequest) {
        service.updateCustomer(id, customer)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: String) {
        service.deleteCustomer(id)
    }
}