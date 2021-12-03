package com.ml.fundamentos

class Pessoa(private var nome: String) {
    override fun toString(): String {
        return "Pessoa(nome='$nome')"
    }
}

fun main() {
    println(Pessoa("Ramon"))
}