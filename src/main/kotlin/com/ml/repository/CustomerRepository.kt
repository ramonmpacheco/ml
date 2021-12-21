package com.ml.repository

import com.ml.model.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Int>{
    fun findByNameContainingIgnoreCase(name: String): List<Customer>;

}