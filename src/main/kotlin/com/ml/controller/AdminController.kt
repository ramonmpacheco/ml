package com.ml.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController() {

    @GetMapping("/report")
    fun allCustomers(@RequestParam name: String?): String {
        return "report admin"
    }
}