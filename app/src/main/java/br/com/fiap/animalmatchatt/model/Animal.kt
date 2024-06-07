package br.com.fiap.animalmatchatt.model

data class Animal(
    val _id: String?,
    val name: String,
    val type: String,
    val race: String,
    val sex: String,
    val age: String,
    val description: String,
    val image: String?,
    val owner: String?
)

data class AnimalResponse(
    val animals: List<Animal>
)

data class UpdateAnimalRequest(
    val name: String,
    val type: String,
    val race: String,
    val sex: String,
    val age: String,
    val image: String,
    val owner: String?
)

data class AnimalListResponse(
    val animals: List<Animal>
)

data class AnimalByNameRequest(
    val name: String
)

data class DeleteAnimalResponse(
    val success: Boolean,
    val message: String
)
