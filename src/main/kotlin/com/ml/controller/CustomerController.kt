package com.ml.controller

import com.ml.controller.request.PostCustomerRequest
import com.ml.controller.request.PutCustomerRequest
import com.ml.extension.toCustomer
import com.ml.model.Customer
import com.ml.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(val service: CustomerService) {

    @GetMapping
    fun allCustomers(@RequestParam name: String?): List<Customer> {
        return service.allCustomers(name)
    }

    @GetMapping("/{id}")
    fun customer(@PathVariable id: Int): Customer {
        return service.customer(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        service.createCustomer(customer.toCustomer())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) {
        service.updateCustomer(customer.toCustomer(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Int) {
        service.deleteCustomer(id)
    }
}