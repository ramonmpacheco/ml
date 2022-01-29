package com.ml.controller.request

import com.ml.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class PostCustomerRequest(
    @field:NotBlank
    var name: String,
    @field:Email
    @EmailAvailable
    var email: String
)