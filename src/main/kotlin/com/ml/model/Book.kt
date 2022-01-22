package com.ml.model

import com.ml.enums.BookStatus
import com.ml.enums.BookStatus.CANCELADO
import com.ml.enums.BookStatus.DELETADO
import com.ml.enums.Errors.ML003
import com.ml.exceptions.BadRequestException
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "book")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column
    var name: String,
    @Column
    var price: BigDecimal,
    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
) {
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if (field == CANCELADO || field == DELETADO) {
                throw BadRequestException(ML003.message.format(field, value), ML003.code)
            }

            field = value
        }

    constructor(
        id: Int? = null,
        name: String,
        price: BigDecimal,
        customer: Customer? = null,
        status: BookStatus?
    ) : this(id, name, price, customer) {
        this.status = status
    }
}