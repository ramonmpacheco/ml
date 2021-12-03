package com.ml.fundamentos

class Repeticao {
    fun forComIn() {
        for (x in 1 .. 10) {
            println(x)
        }
    }

    fun forComStep() {
        println("---------------")
        for(x in 2 .. 10 step 2) {
            println(x)
        }
    }
}

fun main() {
    val repeticao = Repeticao()

    repeticao.forComIn()
    repeticao.forComStep()
}