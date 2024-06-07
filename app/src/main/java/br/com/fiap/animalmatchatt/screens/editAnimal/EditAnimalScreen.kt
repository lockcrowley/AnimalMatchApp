package br.com.fiap.animalmatchatt.screens.editAnimal

import android.net.Uri
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.ButtonComponent
import br.com.fiap.animalmatchatt.components.TextFieldComponent
import br.com.fiap.animalmatchatt.components.TitleComponent
import br.com.fiap.animalmatchatt.model.Animal
import br.com.fiap.animalmatchatt.model.EditResponse
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.User
import br.com.fiap.animalmatchatt.model.UserLoginReturn
import br.com.fiap.animalmatchatt.services.AnimalService
import br.com.fiap.animalmatchatt.services.RetrofitFactory
import br.com.fiap.animalmatchatt.services.UserService
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLDecoder

@Composable
fun EditAnimalScreen(navController: NavController, animalJson: String) {
    val context = LocalContext.current.applicationContext
    val tokenManager = TokenManager(context)
    val token = tokenManager.getAccessToken()
    val decodedUserJson = URLDecoder.decode(animalJson, "UTF-8")
    val animal = Gson().fromJson(decodedUserJson, Animal::class.java)
    val retrofitFactory = RetrofitFactory()
    val animalService = retrofitFactory.create(AnimalService::class.java)

    var animalName by remember() {
        mutableStateOf(animal.name)
    }

    var animalType by remember {
        mutableStateOf(animal.type)
    }

    var animalRace by remember {
        mutableStateOf(animal.race)
    }

    var animalSex by remember {
        mutableStateOf(animal.sex)
    }

    var animalAge by remember {
        mutableStateOf(animal.age)
    }

    var animalDesc by remember {
        mutableStateOf(animal.description)
    }

    val animalUpdated = Animal(
        _id = null,
        name = animalName,
        type = animalType,
        race = animalRace,
        sex = animalSex,
        age = animalAge,
        description = animalDesc,
        image = null,
        owner = null
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
                title = "Edite os dados do seu pet",
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
                        placeholderValue =  "Descrição do pet",
                        iconImage = painterResource(id = drawable.dog_resting_on_a_pet_hotel_bed_svgrepo_com)
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    ButtonComponent(
                        textField = "Editar",
                        fontTextButton = 20.sp,
                        colorButton = color.orange,
                        onClick = {
                            animalService.editAnimal(token, id = animal._id, animalUpdated).enqueue(object : Callback<Animal> {
                                override fun onResponse(call: Call<Animal>, response: Response<Animal>) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(context, "Animal atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                                    } else {
                                        val errorBody = response.errorBody()?.string()
                                        val errorMessage = if (errorBody != null) {
                                            try {
                                                val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                                                errorResponse.error
                                            } catch (e: Exception) {
                                                "Erro ao atualizar animal"
                                            }
                                        } else {
                                            "Erro ao atualizar animal"
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

                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}