package com.ml.repository

import com.ml.model.Purchase
import org.springframework.data.jpa.repository.JpaRepository

interface PurchaseRepository : JpaRepository<Purchase, Int> {

}
