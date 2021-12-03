package com.ml.fundamentos

fun main() {
    val x = 10

    when(x) { // posso retornar o resultado do when também
        5 -> println("5")
        6,7 -> println("Agrupado")
        in 2 ..4 -> println("Range")
        else -> println("Clone do switch case")
    }
}