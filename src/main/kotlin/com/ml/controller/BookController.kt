package com.ml.controller

import com.ml.controller.request.PostBookRequest
import com.ml.extension.toBookModel
import com.ml.service.BookService
import com.ml.service.CustomerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(
    var bookService: BookService,
    val customerService: CustomerService
) {
    @PostMapping
    fun create(@RequestBody request: PostBookRequest) {
        val customer = customerService.getCustomerById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }
}