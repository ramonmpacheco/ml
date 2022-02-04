package com.ml.events.listeners

import com.ml.events.PurchaseEvent
import com.ml.service.BookService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UpdateSoldBookListener(private val bookService: BookService) {
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {


        bookService.purchase(purchaseEvent.purchase.books)
    }
}