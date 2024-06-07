package br.com.fiap.animalmatchatt.screens.editProfile

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.ButtonComponent
import br.com.fiap.animalmatchatt.components.TextFieldComponent
import br.com.fiap.animalmatchatt.components.TitleComponent
import br.com.fiap.animalmatchatt.model.EditResponse
import br.com.fiap.animalmatchatt.model.EditUser
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.User
import br.com.fiap.animalmatchatt.model.UserLoginReturn
import br.com.fiap.animalmatchatt.services.RetrofitFactory
import br.com.fiap.animalmatchatt.services.UserService
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLDecoder

@Composable
fun EditProfileScreen(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val tokenManager = TokenManager(context)
    val userJson = tokenManager.getUser()
    val token = tokenManager.getAccessToken()
    val retrofitFactory = RetrofitFactory()
    val userService = retrofitFactory.create(UserService::class.java)

    val decodedUserJson = userJson?.let {
        URLDecoder.decode(it, "UTF-8")
    } ?: ""

    val user = Gson().fromJson(decodedUserJson, UserLoginReturn::class.java)

    var userName by remember() {
        mutableStateOf(user.name)
    }

    var userEmail by remember {
        mutableStateOf(user.email)
    }

    var userPhone by remember {
        mutableStateOf(user.phone)
    }

    var userResidence by remember {
        mutableStateOf(user.residence)
    }

    var userWantAdopt by remember {
        mutableStateOf(user.wantToAdopt)
    }

    var userStreet by remember {
        mutableStateOf(user.address.street)
    }

    var userCity by remember {
        mutableStateOf(user.address.city)
    }

    var userState by remember {
        mutableStateOf(user.address.state)
    }

    var userCountry by remember {
        mutableStateOf(user.address.country)
    }

    var userZipCode by remember {
        mutableStateOf(user.address.zipCode)
    }

    var userDesc by remember {
        mutableStateOf(user.description)
    }

    var isOng = user.isOng

    val destinationScreen = if (isOng == true) {
        "profileOng"
    } else {
        "profileUser"
    }

    val newUser = EditUser(
        name = userName,
        email = userEmail,
        phone = userPhone,
        residence = userResidence,
        wantToAdopt = userWantAdopt,
        street = userStreet,
        city = userCity,
        state = userState,
        country = userCountry,
        zipCode = userZipCode,
        description = userDesc,
        hashtags = null,
        resetToken = "",
        image = null,
        isOng = isOng!!,
        adoptedAnimals = null
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            TitleComponent(
                title = "Edite seu perfil",
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
                        fieldValue = userResidence,
                        onFieldChange = {
                            userResidence = it
                        },
                        placeholderValue =  "Residência",
                        iconImage = painterResource(id = drawable.baseline_house_24),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = userWantAdopt,
                        onFieldChange = {
                            userWantAdopt = it
                        },
                        placeholderValue =  "O que quer adotar?",
                        iconImage = painterResource(id = drawable.dog_resting_on_a_pet_hotel_bed_svgrepo_com),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
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
                        fieldValue = userDesc!!,
                        onFieldChange = {
                            userDesc = it
                        },
                        placeholderValue =  "Descrição",
                        iconImage = painterResource(id = drawable.baseline_short_text_24)
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    ButtonComponent(
                        textField = "Editar",
                        fontTextButton = 20.sp,
                        colorButton = color.orange,
                        onClick = {
                            userService.editProfile(token, user = newUser).enqueue(object : Callback<EditResponse> {
                                override fun onResponse(call: Call<EditResponse>, response: Response<EditResponse>) {
                                    if (response.isSuccessful) {
                                        val loginResponse = response.body()
                                        val updatedUser = loginResponse?.user
                                        val userJsonUpdated = Uri.encode(Gson().toJson(updatedUser))

                                        if (token != null && userJsonUpdated != null) {
//                                            tokenManager.clearUser()
                                            tokenManager.saveAccessToken(token, userJsonUpdated)

                                            Toast.makeText(context, "Perfil atualizado com sucesso!", Toast.LENGTH_SHORT).show()

                                            navController.navigate(destinationScreen)
                                        }

                                    } else {
                                        val errorBody = response.errorBody()?.string()
                                        val errorMessage = if (errorBody != null) {
                                            try {
                                                val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                                                errorResponse.error
                                            } catch (e: Exception) {
                                                "Erro ao atualizar usuário"
                                            }
                                        } else {
                                            "Erro ao atualizar usuário"
                                        }
                                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<EditResponse>, t: Throwable) {
                                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}