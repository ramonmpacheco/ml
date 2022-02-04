package com.ml.mappers

import com.ml.controller.request.PostPurchaseRequest
import com.ml.model.Purchase
import com.ml.service.BookService
import com.ml.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(private val bookService: BookService, private val customerService: CustomerService) {
    fun toModel(request: PostPurchaseRequest): Purchase {
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)

        return Purchase(
            customer = customer,
            books = books.toMutableList(),
            price = books.sumOf { it.price }
        )
    }

}
