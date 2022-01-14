package com.ml.controller.response

import com.ml.enums.BookStatus
import com.ml.model.Customer
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: Customer? = null,
    var status: BookStatus? = null
)
