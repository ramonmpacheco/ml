package com.ml.service

import com.ml.model.Customer
import org.springframework.stereotype.Service

@Service
class CustomerService {
    val customers = mutableListOf<Customer>();

    fun allCustomers(name: String?): List<Customer> {
        name?.let { return customers.filter { it.name.contains(name, true) } }
        return customers
    }

    fun customer(id: Int): Customer {
        return customers.first { c -> c.id == id }
    }

    fun createCustomer(customer: Customer) {
        customers.add(customer)
        println(customer)
    }

    fun updateCustomer(customer: Customer) {
        customers.first { it.id == customer.id }.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun deleteCustomer(id: Int) {
        customers.removeIf { c -> c.id == id }
    }
}