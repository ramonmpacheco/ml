package com.ml.fundamentos

class IfElse {
    fun ehParOuImpar(numero: Int): String {
        return if (numero % 2 ==0)
            "Par"
        else
            "Impar"
    }
}

fun main() {
    var ifElse = IfElse()
    println(ifElse.ehParOuImpar(11))
}