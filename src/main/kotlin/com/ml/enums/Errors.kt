package com.ml.enums

enum class Errors(val code:String, val message:String) {
    ML000("ML-000", "Access denied"),
    ML001("ML-001", "Livro não %s encontrado"),
    ML002("ML-002", "Cliente %s não encontrado"),
    ML003("ML-003", "Status %s cannot be changed to %s"),
    ML004("ML-004", "Invalid request")
}