package br.com.fiap.animalmatchatt.model

data class ProcessAd(
    val _id: String,
    val status: String,
    val createdAt: String,
    val days: Int,
    val adopter: String,
    val animal: String,
    val animalName: String,
)

data class ProcessResponse(
    val processes: List<ProcessAd>
)

data class DaysUpdate(
    val days: Int
)
