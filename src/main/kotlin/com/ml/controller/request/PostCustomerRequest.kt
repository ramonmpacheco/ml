package com.ml.controller.request

import com.ml.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotBlank
    var name: String,
    @field:Email
    @EmailAvailable
    var email: String,
    @field:NotEmpty(message = "Senha inválida")
    var password: String
)