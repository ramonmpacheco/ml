package com.ml.extension

import com.ml.controller.request.PostBookRequest
import com.ml.controller.request.PostCustomerRequest
import com.ml.controller.request.PutBookRequest
import com.ml.controller.request.PutCustomerRequest
import com.ml.controller.response.BookResponse
import com.ml.controller.response.CustomerResponse
import com.ml.enums.BookStatus
import com.ml.enums.CustomerStatus
import com.ml.model.Book
import com.ml.model.Customer

fun PostCustomerRequest.toCustomer(): Customer {
    return Customer(
        name = this.name,
        email = this.email,
        status = CustomerStatus.ATIVO,
        password = this.password
    )
}

fun PutCustomerRequest.toCustomer(previousCustomer: Customer): Customer {
    return Customer(
        id = previousCustomer.id,
        name = this.name,
        email = this.email,
        status = previousCustomer.status,
        password = previousCustomer.password
    )
}

fun Customer.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
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

fun Book.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}