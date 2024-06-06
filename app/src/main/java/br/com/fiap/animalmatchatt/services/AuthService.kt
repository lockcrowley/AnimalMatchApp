package br.com.fiap.animalmatchatt.services
import br.com.fiap.animalmatchatt.model.ForgotPasswordRequest
import br.com.fiap.animalmatchatt.model.LoginRequest
import br.com.fiap.animalmatchatt.model.LoginResponse
import br.com.fiap.animalmatchatt.model.ResetPasswordRequest
import br.com.fiap.animalmatchatt.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PATCH
interface AuthService {
    @POST("auth/create")
    fun createUser(@Body user: User): Call<User>

    @POST("auth/login")
    fun userLogin(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("auth/logout")
    fun userLogout(): Call<Void>

    @PATCH("auth/forgot-password")
    fun forgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest): Call<Void>

    @PATCH("auth/reset-password")
    fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Call<Void>
}