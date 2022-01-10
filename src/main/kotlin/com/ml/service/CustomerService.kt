package com.ml.service

import com.ml.enums.CustomerStatus
import com.ml.model.Customer
import com.ml.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository, val bookService: BookService) {

    fun allCustomers(name: String?): List<Customer> {
        name?.let { return customerRepository.findByNameContainingIgnoreCase(name) }

        return customerRepository.findAll().toList()
    }

    fun findById(id: Int): Customer {
        return customerRepository.findById(id).orElseThrow { RuntimeException("Customer não encontrado") }
    }

    fun createCustomer(customer: Customer) {
        customerRepository.save(customer)
    }

    fun updateCustomer(customer: Customer) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw RuntimeException("Customer não existe")
        }
        customerRepository.save(customer)
    }

    fun deleteCustomer(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }
}