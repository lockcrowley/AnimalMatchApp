package br.com.fiap.animalmatchatt.model
import com.google.gson.annotations.SerializedName
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

data class EditResponse(
    val user: UserLoginReturn
)

data class ResetPasswordRequest(
    val token: String,
    val newPassword: String
)

data class ForgotPasswordRequest(
    val email: String
)

data class PasswordChangeRequest(
    @SerializedName("oldPassword")
    val oldPassword: String,
    @SerializedName("newPassword")
    val newPassword: String
)

