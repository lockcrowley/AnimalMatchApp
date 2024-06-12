package br.com.fiap.animalmatchatt.model

data class Process(
    val _id: String,
    val adopterId: String,
    val animalId: String,
    val status: String,
    val createdAt: String,
    val days: Int
)

data class DaysUpdate(
    val days: Int
)
