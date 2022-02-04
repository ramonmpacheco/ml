package com.ml.service

import com.ml.enums.BookStatus
import com.ml.enums.Errors
import com.ml.exceptions.NotFoundException
import com.ml.model.Book
import com.ml.model.Customer
import com.ml.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: Book) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<Book> {
        return bookRepository.findAll(pageable)
    }

    fun findAllActive(): List<Book> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
    }

    fun findById(id: Int): Book {
        return bookRepository.findById(id).orElseThrow {
            NotFoundException(Errors.ML001.message.format(id), Errors.ML001.code)
        }
    }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO
        update(book)
    }

    fun update(book: Book) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: Customer) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }

    fun findAllByIds(bookIds: Set<Int>): List<Book> {
        return bookRepository.findAllById(bookIds).toList()
    }

}
