package com.ml.service

import com.ml.enums.CustomerStatus
import com.ml.enums.Roles
import com.ml.exceptions.NotFoundException
import com.ml.model.Customer
import com.ml.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

    @Test
    fun `should return customer when name is informed`() {
        val name = "Pedro"
        val fakeCustomer = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findByNameContainingIgnoreCase(name) } returns fakeCustomer
        val customers = customerService.allCustomers(name)

        assertEquals(fakeCustomer, customers)
        verify(exactly = 0) { customerRepository.findAll() }
        verify(exactly = 1) { customerRepository.findByNameContainingIgnoreCase(name) }
    }

    @Test
    fun `should create customer and encrypt password`() {
        val initialPassword = Math.random().toString()
        val fakeCustomer = buildCustomer(password = initialPassword)
        val fakePassword = UUID.randomUUID().toString()
        val fakeCustomerEncrypted = fakeCustomer.copy(password = fakePassword)

        every { customerRepository.save(fakeCustomerEncrypted) } returns fakeCustomer
        every { bCrypt.encode(initialPassword) } returns fakePassword
        customerService.createCustomer(fakeCustomer)

        verify(exactly = 1) { customerRepository.save(any()) }
        verify(exactly = 1) { bCrypt.encode(any()) }
    }

    @Test
    fun `should return customer by id`() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)
        every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)
        val customer = customerService.findById(id)
        assertEquals(fakeCustomer, customer)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should throw exception when customer not found`() {
        val id = Random().nextInt()

        every { customerRepository.findById(id) } returns Optional.empty()

        val error = assertThrows<NotFoundException> { customerService.findById(id) }
        assertEquals("Cliente $id não encontrado", error.message)
        assertEquals("ML-002", error.errorCode)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should update customer`() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)

        every { customerRepository.existsById(id) } returns true
        every { customerRepository.save(fakeCustomer) } returns fakeCustomer

        customerService.updateCustomer(fakeCustomer)

        verify(exactly = 1) { customerRepository.existsById(id) }
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
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