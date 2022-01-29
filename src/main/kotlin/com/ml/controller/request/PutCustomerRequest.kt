package com.ml.controller.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class PutCustomerRequest(
    @field:NotBlank
    var name: String,
    @field:Email
    var email: String
)