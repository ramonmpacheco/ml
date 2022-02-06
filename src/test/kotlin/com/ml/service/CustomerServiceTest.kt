package com.ml.service

import com.ml.enums.CustomerStatus
import com.ml.enums.Roles
import com.ml.model.Customer
import com.ml.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK
    private lateinit var bookService: BookService

    @MockK
    private lateinit var bCrypt: BCryptPasswordEncoder

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @InjectMockKs
    private lateinit var customerService: CustomerService

    @Test
    fun `should return all customers`() {
        val fakeCustomer = listOf(buildCustomer(), buildCustomer())
        every { customerRepository.findAll() } returns fakeCustomer
        val customers = customerService.allCustomers(null)

        assertEquals(fakeCustomer, customers)
        verify(exactly = 1) { customerRepository.findAll() }
        verify(exactly = 0) { customerRepository.findByNameContainingIgnoreCase(any()) }
    }

    private fun buildCustomer(
        id: Int? = null,
        name: String = "customer name",
        email: String = "${UUID.randomUUID()}@gmail.com",
        password: String = "password"
    ) = Customer(
        id = id,
        name = name,
        email = email,
        password = password,
        status = CustomerStatus.ATIVO,
        roles = setOf(Roles.CUSTOMER)
    )

}