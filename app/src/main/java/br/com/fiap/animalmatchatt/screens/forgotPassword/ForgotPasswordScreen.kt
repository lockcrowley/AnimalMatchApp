package br.com.fiap.animalmatchatt.screens.forgotPassword

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
import br.com.fiap.animalmatchatt.components.TitleComponent
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.ForgotPasswordRequest
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
fun ForgotPasswordScreen (navController: NavController) {
    val retrofitFactory = RetrofitFactory()
    val authService = retrofitFactory.create(AuthService::class.java)
    val context = LocalContext.current.applicationContext

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    var userEmail by remember {
        mutableStateOf("")
    }

    val forgotRequest = ForgotPasswordRequest(
        email = userEmail,
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
                    text = "Recupere sua senha",
                    color = colorResource(id = color.white),
                    fontFamily = poppyns,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold
                )

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

                Spacer(modifier = Modifier.height(60.dp))

                ButtonComponent(
                    textField = "Enviar",
                    fontTextButton = 20.sp,
                    colorButton = color.orange,
                    onClick = {
                        authService.forgotPassword(forgotPasswordRequest = forgotRequest).enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "E-mail enviado com sucesso!", Toast.LENGTH_SHORT).show()
                                    navController.popBackStack()
                                    navController.navigate("reset")
                                } else {
                                    val errorBody = response.errorBody()?.string()
                                    val errorMessage = if (errorBody != null) {
                                        try {
                                            val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                                            errorResponse.error
                                        } catch (e: Exception) {
                                            "Erro ao tentar enviar e-mail, tente novamente mais tarde!"
                                        }
                                    } else {
                                        "Erro ao tentar enviar e-mail, tente novamente mais tarde!"
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