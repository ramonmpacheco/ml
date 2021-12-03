package com.ml.fundamentos

class Funcoes {
    fun imprimir() { // por padrão retorna unit que seria o void
        println("Hello")
    }

    fun imprimirParam(texto: String) {
        println(texto);
    }

    fun paraMaiusculo(texto: String): String {
        return  texto.uppercase()
    }

    fun concatene(texto: String): String {
        return "Estou concatenando com: $texto"
    }

    fun namedParameter(texto: String, texto2: String = "padrão") {
        println("$texto $texto2")
    }
}

fun main() {
    val funcoes = Funcoes()
    funcoes.imprimir()
    funcoes.imprimirParam("EAE")
    println(funcoes.paraMaiusculo("bom dia"))
    println(funcoes.concatene("Hey"))
    funcoes.namedParameter(texto2 = "segundo", texto = "primeiro")
    funcoes.namedParameter(texto = "primeiro")
}