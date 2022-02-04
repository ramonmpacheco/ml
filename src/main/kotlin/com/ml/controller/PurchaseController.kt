package com.ml.controller

import com.ml.controller.request.PostPurchaseRequest
import com.ml.mappers.PurchaseMapper
import com.ml.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController("/purchases")
class PurchaseController(private val purchaseService: PurchaseService, private val purchaseMapper: PurchaseMapper) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody request: PostPurchaseRequest) {
        purchaseService.create(purchaseMapper.toModel(request))
    }
}