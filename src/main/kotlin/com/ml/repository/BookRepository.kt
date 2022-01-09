package com.ml.repository

import com.ml.enums.BookStatus
import com.ml.model.Book
import com.ml.model.Customer
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Int> {
    fun findByStatus(ativo: BookStatus): List<Book>
    fun findByCustomer(customer: Customer): List<Book>
}