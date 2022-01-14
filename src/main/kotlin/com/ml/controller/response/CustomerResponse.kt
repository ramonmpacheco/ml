package com.ml.controller.response

import com.ml.enums.CustomerStatus

data class CustomerResponse(
    var id: Int? = null,
    var name: String,
    var email: String,
    var status: CustomerStatus
)
