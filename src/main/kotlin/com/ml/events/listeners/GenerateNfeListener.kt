package com.ml.events.listeners

import com.ml.events.PurchaseEvent
import com.ml.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(private val purchaseService: PurchaseService) {
    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
       val nfe = UUID.randomUUID().toString()
        val purchase = purchaseEvent.purchase.copy(nfe = nfe)

        purchaseService.update(purchase)
    }
}