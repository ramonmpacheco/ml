package com.ml.fundamentos

class Carro(val cor:String, val ano: Int) {
    // val já é final
}

fun main() {
    var carro  = Carro("Cinza", 2010)
    println(carro.ano)
}