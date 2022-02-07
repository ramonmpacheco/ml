package com.ml.service

import com.ml.enums.CustomerStatus
import com.ml.enums.Errors
import com.ml.enums.Roles
import com.ml.exceptions.NotFoundException
import com.ml.model.Customer
import com.ml.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCrypt: BCryptPasswordEncoder
) {

    fun allCustomers(name: String?): List<Customer> {
        name?.let { return customerRepository.findByNameContainingIgnoreCase(name) }

        return customerRepository.findAll().toList()
    }

    fun findById(id: Int): Customer {
        return customerRepository.findById(id).orElseThrow { NotFoundException(Errors.ML002.message.format(id), Errors.ML002.code) }
    }

    fun createCustomer(customer: Customer) {
        val copy = customer.copy(
            roles = setOf(Roles.CUSTOMER),
            password = bCrypt.encode(customer.password)
        )
        customerRepository.save(copy)
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

    fun emailAvailable(value: String): Boolean {
        return !customerRepository.existsByEmail(value)
    }
}