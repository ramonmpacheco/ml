package com.ml.extension

import com.ml.controller.request.PostBookRequest
import com.ml.controller.request.PostCustomerRequest
import com.ml.controller.request.PutBookRequest
import com.ml.controller.request.PutCustomerRequest
import com.ml.enums.BookStatus
import com.ml.model.Book
import com.ml.model.Customer

fun PostCustomerRequest.toCustomer(): Customer {
    return Customer(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomer(id: Int): Customer {
    return Customer(id = id, name = this.name, email = this.email)
}

fun PostBookRequest.toBookModel(customer: Customer): Book {
    return Book(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(previousBook: Book): Book {
    return Book(
        id = previousBook.id,
        name = this.name ?: previousBook.name,
        price = this.price ?: previousBook.price,
        status = previousBook.status,
        customer = previousBook.customer
    )
}