package com.ml.extension

import com.ml.controller.request.PostCustomerRequest
import com.ml.controller.request.PutCustomerRequest
import com.ml.model.Customer

fun PostCustomerRequest.toCustomer(): Customer {
    return Customer(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomer(id: String): Customer {
    return Customer(id = id, name = this.name, email = this.email)
}