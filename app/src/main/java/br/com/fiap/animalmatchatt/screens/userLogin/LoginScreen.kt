package br.com.fiap.animalmatchatt.screens.userLogin

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.ButtonComponent
import br.com.fiap.animalmatchatt.components.HeaderAuthComponent
import br.com.fiap.animalmatchatt.components.TextFieldComponent
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.LoginRequest
import br.com.fiap.animalmatchatt.model.LoginResponse
import br.com.fiap.animalmatchatt.services.AuthService
import br.com.fiap.animalmatchatt.services.RetrofitFactory
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun LoginScreen (navController: NavController) {
    val retrofitFactory = RetrofitFactory()
    val authService = retrofitFactory.create(AuthService::class.java)
    val context = LocalContext.current.applicationContext

    val tokenManager = TokenManager(context)

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    var userEmail by remember {
        mutableStateOf("")
    }

    var userPassword by remember {
        mutableStateOf("")
    }

    val loginUser = LoginRequest(
        email = userEmail,
        password = userPassword
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderAuthComponent(navController, icon = false)

        Box(
            modifier = Modifier.weight(1f, fill = true)
        ) {
            Image(
                painter = painterResource(id = drawable.login),
                contentDescription = "login",
                modifier = Modifier
                    .size(280.dp)
            )
        }

        Box (
            modifier = Modifier
                .weight(1.9f, fill = true)
                .background(colorResource(id = color.green_bold))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                TextFieldComponent(
                    fieldValue = userEmail,
                    onFieldChange = {
                        userEmail = it
                    },
                    placeholderValue =  "E-mail",
                    iconImage = painterResource(id = drawable.baseline_email_24),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                )

                Spacer(modifier = Modifier.height(30.dp))

                TextFieldComponent(
                    fieldValue = userPassword,
                    onFieldChange = {
                        userPassword = it
                    },
                    placeholderValue =  "Password",
                    iconImage = painterResource(id = drawable.baseline_lock_person_24),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(10.dp))

                ClickableText(
                    text = AnnotatedString(
                        text = "Esqueceu a senha?",
                        spanStyle = SpanStyle(
                            color = colorResource(id = color.white),
                            textDecoration = TextDecoration.Underline,
                            fontFamily = poppyns,
                            fontSize = 15.sp
                        ),
                    ),
                    onClick = {
                        navController.navigate("forgot")
                    },
                    modifier = Modifier.offset(x = (-80).dp)
                )

                Spacer(modifier = Modifier.height(60.dp))

                ButtonComponent(
                    textField = "Entrar",
                    fontTextButton = 20.sp,
                    colorButton = color.orange,
                    onClick = {
                        authService.userLogin(loginRequest = loginUser).enqueue(object : Callback<LoginResponse> {
                            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                                if (response.isSuccessful) {
                                    val loginResponse = response.body()
                                    val token = loginResponse?.accessToken
                                    val user = loginResponse?.user
                                    val userJson = Uri.encode(Gson().toJson(user))

                                    tokenManager.saveAccessToken(token, userJson)
                                    navController.popBackStack()
                                    navController.navigate("drawer")
                                } else {
                                    val errorBody = response.errorBody()?.string()
                                    val errorMessage = if (errorBody != null) {
                                        try {
                                            val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                                            errorResponse.error
                                        } catch (e: Exception) {
                                            "Erro ao tentar fazer o login, tente novamente!"
                                        }
                                    } else {
                                        "Erro ao tentar fazer login, tente novamente!"
                                    }
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                )

                Spacer(modifier = Modifier.height(15.dp))

                ClickableText(
                    text = AnnotatedString(
                        text = "Não tem conta? Cadastre-se!",
                        spanStyle = SpanStyle(
                            color = colorResource(id = color.white),
                            textDecoration = TextDecoration.Underline,
                            fontFamily = poppyns,
                            fontSize = 15.sp
                        ),
                    ),
                    onClick = {
                        navController.navigate("registerUser")
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}