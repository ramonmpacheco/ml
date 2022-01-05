package com.ml.service

import com.ml.enums.BookStatus
import com.ml.model.Book
import com.ml.repository.BookRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: Book) {
        bookRepository.save(book)
    }

    fun findAll(): List<Book> {
        return bookRepository.findAll().toList()
    }

    fun findAllActive(): List<Book> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
    }

    fun findById(id: Int): Book {
        return bookRepository.findById(id).orElseThrow { RuntimeException("Livro não encontrado") }
    }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO
        update(book)
    }

    fun update(book: Book) {
        bookRepository.save(book)
    }

}
