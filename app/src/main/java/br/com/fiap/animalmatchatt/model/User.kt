package br.com.fiap.animalmatchatt.model

data class User(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val description: String?,
    val hashtags: List<String>?,
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String,
    val resetToken: String?,
    val image: String?,
    val isOng: Boolean?,
    val adoptedAnimals: Int?
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val user: User
)

data class ResetPasswordRequest(
    val token: String,
    val newPassword: String
)

data class ForgotPasswordRequest(
    val email: String
)
