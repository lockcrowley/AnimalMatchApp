package br.com.fiap.animalmatchatt.model
data class User(
    val _id: String?,
    val name: String,
    val email: String,
    val password: String?,
    val phone: String,
    val residence: String,
    val wantToAdopt: String,
    val description: String?,
    val hashtags: List<String>?,
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String,
    val resetToken: String?,
    val image: String?,
    val isOng: Boolean,
    val adoptedAnimals: Int?
)

data class Address(
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String,
)

data class UserLoginReturn(
    val _id: String?,
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val residence: String,
    val wantToAdopt: String,
    val description: String?,
    val hashtags: List<String>?,
    val address: Address,
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
    val accessToken: String,
    val user: UserLoginReturn
)

data class UserReturn(
    val user: UserLoginReturn
)

data class EditResponse(
    val user: UserLoginReturn
)

data class EditUser(
    val name: String,
    val email: String,
    val phone: String,
    val residence: String,
    val wantToAdopt: String,
    val description: String?,
    val hashtags: List<String>?,
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String,
    val resetToken: String?,
    val image: String?,
    val isOng: Boolean,
    val adoptedAnimals: Int?
)

data class ResetPasswordRequest(
    val resetToken: String,
    val password: String
)

data class ForgotPasswordRequest(
    val email: String
)

data class PasswordChangeRequest(
    val currentPassword: String,
    val newPassword: String,
    val confirmPassword: String
)

