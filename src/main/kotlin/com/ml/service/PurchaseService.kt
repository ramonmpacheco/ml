package com.ml.service

import com.ml.events.PurchaseEvent
import com.ml.model.Purchase
import com.ml.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun create(purchase: Purchase) {
        purchaseRepository.save(purchase)
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchase))
    }

}
