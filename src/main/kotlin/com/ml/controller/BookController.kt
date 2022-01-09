package com.ml.controller

import com.ml.controller.request.PostBookRequest
import com.ml.controller.request.PutBookRequest
import com.ml.extension.toBookModel
import com.ml.model.Book
import com.ml.service.BookService
import com.ml.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
    var bookService: BookService,
    val customerService: CustomerService
) {
    @PostMapping
    fun create(@RequestBody request: PostBookRequest) {
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun findAll(): List<Book> {
        return bookService.findAll()
    }

    @GetMapping("/active")
    fun findAllActive(): List<Book> = bookService.findAllActive()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Book = bookService.findById(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody request: PutBookRequest) {
        bookService.update(request.toBookModel(bookService.findById(id)))
    }
}