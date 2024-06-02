package br.com.fiap.animalmatchatt.screens.registerUsers

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import br.com.fiap.animalmatchatt.components.HeaderAuthComponent
import br.com.fiap.animalmatchatt.components.TextFieldComponent
import br.com.fiap.animalmatchatt.components.TitleComponent
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.User
import br.com.fiap.animalmatchatt.services.AuthService
import br.com.fiap.animalmatchatt.services.RetrofitFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RegisterUsersScreen(navController: NavController) {
    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    var userName by remember() {
        mutableStateOf("Alifer Santana")
    }

    var userEmail by remember {
        mutableStateOf("aliferass03@gmail.com")
    }

    var userPassword by remember {
        mutableStateOf("12345678")
    }

    var userPhone by remember {
        mutableStateOf("11982377343")
    }

    var userStreet by remember {
        mutableStateOf("Rua Jose Barreto")
    }

    var userCity by remember {
        mutableStateOf("Taboao da Serra")
    }

    var userState by remember {
        mutableStateOf("Sao Paulo")
    }

    var userCountry by remember {
        mutableStateOf("Brasil")
    }

    var userZipCode by remember {
        mutableStateOf("06770130")
    }

    var userDesc by remember {
        mutableStateOf("amo animais")
    }

    var isOng by remember {
        mutableStateOf(-1)
    }

    var ongOrUser = isOng == 0

    val retrofitFactory = RetrofitFactory()
    val authService = retrofitFactory.create(AuthService::class.java)
    val context = LocalContext.current.applicationContext

    val newUser = User(
        name = userName,
        email = userEmail,
        password = userPassword,
        phone = userPhone,
        street = userStreet,
        city = userCity,
        state = userState,
        country = userCountry,
        zipCode = userZipCode,
        description = userDesc,
        hashtags = null,
        resetToken = "",
        image = "",
        isOng = ongOrUser,
        adoptedAnimals = 0
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderAuthComponent(navController, loginScreen = false)

        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            TitleComponent(
                title = "Cadastre-se",
                colorText = color.gray_title,
                nameProfileFontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(colorResource(id = color.green_bold)),
        ) {
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(modifier = Modifier.height(50.dp))

                    TextFieldComponent(
                        fieldValue = userName,
                        onFieldChange = {
                            userName = it
                        },
                        placeholderValue =  "Nome",
                        iconImage = painterResource(id = drawable.baseline_person_outline_24)
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

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = userPhone,
                        onFieldChange = {
                            userPhone = it
                        },
                        placeholderValue =  "Celular",
                        iconImage = painterResource(id = drawable.baseline_local_phone_24),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = userStreet,
                        onFieldChange = {
                            userStreet = it
                        },
                        placeholderValue =  "Rua",
                        iconImage = painterResource(id = drawable.baseline_house_24),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = userCity,
                        onFieldChange = {
                            userCity = it
                        },
                        placeholderValue =  "Cidade",
                        iconImage = painterResource(id = drawable.baseline_location_city_24),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = userState,
                        onFieldChange = {
                            userState = it
                        },
                        placeholderValue =  "Estado",
                        iconImage = painterResource(id = drawable.baseline_location_city_24),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = userCountry,
                        onFieldChange = {
                            userCountry = it
                        },
                        placeholderValue =  "País",
                        iconImage = painterResource(id = drawable.baseline_location_city_24)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = userZipCode,
                        onFieldChange = {
                            userZipCode = it
                        },
                        placeholderValue =  "Cep",
                        iconImage = painterResource(id = drawable.baseline_location_on_24),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = userDesc,
                        onFieldChange = {
                            userDesc = it
                        },
                        placeholderValue =  "Descrição",
                        iconImage = painterResource(id = drawable.baseline_short_text_24)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Row (verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = isOng == 0,
                            onClick = {
                                isOng = 0
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.Black,
                                unselectedColor = Color(0xFFFFFFFF)
                            )
                        )
                        Text(text = "Ong", color = colorResource(id = color.white))

                        RadioButton(
                            selected = isOng == 1,
                            onClick = {
                                isOng = 1
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.Black,
                                unselectedColor = Color(0xFFFFFFFF)
                            )
                        )
                        Text(text = "Usuário Padrão", color = colorResource(id = color.white))
                    }

                    Spacer(modifier = Modifier.height(60.dp))

                    Button(
                        onClick = {
                            authService.createUser(user = newUser).enqueue(object : Callback<User> {
                                override fun onResponse(call: Call<User>, response: Response<User>) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(context, "Perfil criado com sucesso!", Toast.LENGTH_SHORT).show()
                                    } else {
                                        val errorBody = response.errorBody()?.string()
                                        val errorMessage = if (errorBody != null) {
                                            try {
                                                val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                                                errorResponse.error
                                            } catch (e: Exception) {
                                                "Erro ao criar usuário"
                                            }
                                        } else {
                                            "Erro ao criar usuário"
                                        }
                                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<User>, t: Throwable) {
                                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        },
                        modifier = Modifier
                            .width(300.dp)
                            .height(55.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = color.orange))
                    ) {
                        Text(
                            text = "Cadastrar",
                            color = colorResource(id = color.white),
                            fontFamily = poppyns,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}