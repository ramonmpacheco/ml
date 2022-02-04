package com.ml.model

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Purchase(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer,

    @ManyToMany
    @JoinTable(
        name = "purchase_book",
        joinColumns = [JoinColumn(name = "purchase_id")],
        inverseJoinColumns = [JoinColumn(name = "book_id")]
    )
    val books: List<Book>,

    @Column
    val nfe: String? = null,

    @Column
    val price: BigDecimal,

    @Column(name = "created_at")
    val createdAt: LocalDate = LocalDate.now()
)