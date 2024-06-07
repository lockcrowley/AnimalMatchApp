package br.com.fiap.animalmatchatt.screens.resetPassword

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.ButtonComponent
import br.com.fiap.animalmatchatt.components.HeaderAuthComponent
import br.com.fiap.animalmatchatt.components.TextFieldComponent
import br.com.fiap.animalmatchatt.components.TitleComponent
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.LoginRequest
import br.com.fiap.animalmatchatt.model.LoginResponse
import br.com.fiap.animalmatchatt.model.ResetPasswordRequest
import br.com.fiap.animalmatchatt.services.AuthService
import br.com.fiap.animalmatchatt.services.RetrofitFactory
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun ResetPasswordScreen (navController: NavController) {
    val retrofitFactory = RetrofitFactory()
    val authService = retrofitFactory.create(AuthService::class.java)
    val context = LocalContext.current.applicationContext

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    var token by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val resetRequest = ResetPasswordRequest(
        resetToken = token,
        password = password
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderAuthComponent(navController, icon = true)

        Box(
            modifier = Modifier.weight(1f, fill = true)
        ) {
            Image(
                painter = painterResource(id = drawable.login),
                contentDescription = "Forgot",
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

                Text(
                    text = "Digite o token que recebeu no e-mail e resete a senha",
                    color = colorResource(id = color.white),
                    fontFamily = poppyns,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 40.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                TextFieldComponent(
                    fieldValue = token,
                    onFieldChange = {
                        token = it
                    },
                    placeholderValue =  "Token",
                    iconImage = painterResource(id = drawable.baseline_lock_person_24)
                )

                Spacer(modifier = Modifier.height(30.dp))

                TextFieldComponent(
                    fieldValue = password,
                    onFieldChange = {
                        password = it
                    },
                    placeholderValue =  "Password",
                    iconImage = painterResource(id = drawable.baseline_lock_person_24),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(60.dp))

                ButtonComponent(
                    textField = "Resetar senha",
                    fontTextButton = 20.sp,
                    colorButton = color.orange,
                    onClick = {
                        authService.resetPassword(resetPasswordRequest = resetRequest).enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show()
                                    navController.popBackStack()
                                    navController.navigate("login")
                                } else {
                                    val errorBody = response.errorBody()?.string()
                                    Log.d("LoginSuccess", "Token: $response")
                                    val errorMessage = if (errorBody != null) {
                                        try {
                                            val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                                            errorResponse.error
                                        } catch (e: Exception) {
                                            "Erro ao tentar resetar senha, tente novamente!"
                                        }
                                    } else {
                                        "Erro ao tentar resetar senha, tente novamente!"
                                    }
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}