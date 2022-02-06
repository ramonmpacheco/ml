package com.ml.controller

import com.ml.controller.request.PostBookRequest
import com.ml.controller.request.PutBookRequest
import com.ml.controller.response.BookResponse
import com.ml.controller.response.PageResponse
import com.ml.extension.toBookModel
import com.ml.extension.toPageResponse
import com.ml.extension.toResponse
import com.ml.service.BookService
import com.ml.service.CustomerService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<BookResponse> {
        return bookService.findAll(pageable).map { it.toResponse() }.toPageResponse()
    }

    @GetMapping("/active")
    fun findAllActive(): List<BookResponse> = bookService.findAllActive().map { it.toResponse() }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): BookResponse = bookService.findById(id).toResponse()

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