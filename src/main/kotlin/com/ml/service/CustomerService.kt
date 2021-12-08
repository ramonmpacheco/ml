package com.ml.service

import com.ml.controller.request.PostCustomerRequest
import com.ml.controller.request.PutCustomerRequest
import com.ml.model.Customer
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService {
    val customers = mutableListOf<Customer>();

    fun allCustomers(name: String?): List<Customer> {
        name?.let { return customers.filter { it.name.contains(name, true) } }
        return customers
    }

    fun customer(id: String): Customer {
        return customers.first { c -> c.id == id }
    }

    fun createCustomer(customer: PostCustomerRequest) {
        customers.add(Customer(UUID.randomUUID().toString(), customer.name, customer.email))
        println(customer)
    }

    fun updateCustomer(id: String, customer: PutCustomerRequest) {
        customers.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun deleteCustomer(id: String) {
        customers.removeIf { c -> c.id == id }
    }
}