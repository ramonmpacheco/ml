package com.ml.controller

import com.ml.controller.request.PostCustomerRequest
import com.ml.controller.request.PutCustomerRequest
import com.ml.controller.response.CustomerResponse
import com.ml.extension.toCustomer
import com.ml.extension.toResponse
import com.ml.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/customers")
class CustomerController(val service: CustomerService) {

    @GetMapping
    fun allCustomers(@RequestParam name: String?): List<CustomerResponse> {
        return service.allCustomers(name).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun customer(@PathVariable id: Int): CustomerResponse {
        return service.findById(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody @Valid customer: PostCustomerRequest) {
        service.createCustomer(customer.toCustomer())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest) {
        val previousCustomer = service.findById(id)
        service.updateCustomer(customer.toCustomer(previousCustomer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Int) {
        service.deleteCustomer(id)
    }
}