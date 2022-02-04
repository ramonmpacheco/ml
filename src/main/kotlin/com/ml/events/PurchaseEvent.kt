package com.ml.events

import com.ml.model.Purchase
import org.springframework.context.ApplicationEvent

class PurchaseEvent (source: Any, val purchase: Purchase): ApplicationEvent(source)