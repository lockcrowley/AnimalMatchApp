package br.com.fiap.animalmatchatt.screens.animalRegister

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.ButtonComponent
import br.com.fiap.animalmatchatt.components.TextFieldComponent
import br.com.fiap.animalmatchatt.components.TitleComponent
import br.com.fiap.animalmatchatt.model.Animal
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.User
import br.com.fiap.animalmatchatt.model.UserLoginReturn
import br.com.fiap.animalmatchatt.services.AnimalService
import br.com.fiap.animalmatchatt.services.RetrofitFactory
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLDecoder

@Composable
fun AnimalRegisterScreen(navController: NavController) {
    val retrofitFactory = RetrofitFactory()
    val context = LocalContext.current.applicationContext
    val animalService = retrofitFactory.create(AnimalService::class.java)

    val tokenManager = TokenManager(context)
    val token = tokenManager.getAccessToken()
    val userJson = tokenManager.getUser()

    val decodedUserJson = userJson?.let {
        URLDecoder.decode(it, "UTF-8")
    } ?: ""

    val user = Gson().fromJson(decodedUserJson, UserLoginReturn::class.java)

    var animalName by remember {
        mutableStateOf("")
    }

    var animalType by remember {
        mutableStateOf("")
    }

    var animalRace by remember {
        mutableStateOf("")
    }

    var animalSex by remember {
        mutableStateOf("")
    }

    var animalAge by remember {
        mutableStateOf("")
    }

    var animalDesc by remember {
        mutableStateOf("")
    }

    val newAnimal = Animal(
        _id = null,
        name = animalName,
        type = animalType,
        race = animalRace,
        sex = animalSex,
        age = animalAge,
        description = animalDesc,
        image = "",
        owner = user._id
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            TitleComponent(
                title = "Cadastre seu animal",
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
                        fieldValue = animalName,
                        onFieldChange = {
                            animalName = it
                        },
                        placeholderValue =  "Nome do pet",
                        iconImage = painterResource(id = drawable.baseline_pets_24)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = animalType,
                        onFieldChange = {
                            animalType = it
                        },
                        placeholderValue =  "Tipo do pet",
                        iconImage = painterResource(id = drawable.baseline_type_animal_24)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = animalRace,
                        onFieldChange = {
                            animalRace = it
                        },
                        placeholderValue =  "Raça do pet",
                        iconImage = painterResource(id = drawable.sharp_pet_supplies_24)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = animalSex,
                        onFieldChange = {
                            animalSex = it
                        },
                        placeholderValue =  "Sexo do pet",
                        iconImage = painterResource(id = drawable.baseline_type_animal_24)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = animalAge,
                        onFieldChange = {
                            animalAge = it
                        },
                        placeholderValue =  "Idade do pet",
                        iconImage = painterResource(id = drawable.dog_resting_on_a_pet_hotel_bed_svgrepo_com)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextFieldComponent(
                        fieldValue = animalDesc,
                        onFieldChange = {
                            animalDesc = it
                        },
                        placeholderValue =  "Descrição curta do pet",
                        iconImage = painterResource(id = drawable.dog_resting_on_a_pet_hotel_bed_svgrepo_com)
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    ButtonComponent(
                        textField = "Cadastrar",
                        fontTextButton = 20.sp,
                        colorButton = color.orange,
                        onClick = {
                            animalService.createAnimal(token, newAnimal).enqueue(object : Callback<Animal> {
                                override fun onResponse(call: Call<Animal>, response: Response<Animal>) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(context, "Animal criado com sucesso!", Toast.LENGTH_SHORT).show()
                                        navController.navigate("registeredAnimals")
                                    } else {
                                        val errorBody = response.errorBody()?.string()
                                        val errorMessage = if (errorBody != null) {
                                            try {
                                                val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                                                errorResponse.error
                                            } catch (e: Exception) {
                                                "Erro ao criar animal"
                                            }
                                        } else {
                                            "Erro ao criar animal"
                                        }
                                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<Animal>, t: Throwable) {
                                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    )

                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}

