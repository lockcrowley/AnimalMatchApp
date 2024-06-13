package br.com.fiap.animalmatchatt.screens.adoptAnimal

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.ButtonComponent
import br.com.fiap.animalmatchatt.components.HashtagBoxComponent
import br.com.fiap.animalmatchatt.components.ProfileImageComponent
import br.com.fiap.animalmatchatt.model.Animal
import br.com.fiap.animalmatchatt.model.AnimalResponse
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.ProcessAd
import br.com.fiap.animalmatchatt.services.AnimalService
import br.com.fiap.animalmatchatt.services.ProcessService
import br.com.fiap.animalmatchatt.services.RetrofitFactory
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AdoptAnimalScreen(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val retrofitFactory = RetrofitFactory()
    val animalService = retrofitFactory.create(AnimalService::class.java)
    val processService = retrofitFactory.create(ProcessService::class.java)
    val tokenManager = TokenManager(context)
    val token = tokenManager.getAccessToken()

    var listAnimals by remember { mutableStateOf<List<Animal>>(emptyList()) }

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    LaunchedEffect(Unit) {
        val call = animalService.getAnimalsToAdopt(token)
        call.enqueue(object : Callback<AnimalResponse> {
            override fun onResponse(call: Call<AnimalResponse>, response: Response<AnimalResponse>) {
                if (response.isSuccessful) {
                    listAnimals = response.body()?.animals ?: emptyList()
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = if (errorBody != null) {
                        try {
                            val errorResponse =
                                Gson().fromJson(errorBody, ErrorResponse::class.java)
                            errorResponse.error
                        } catch (e: Exception) {
                            "Erro ao buscar animais"
                        }
                    } else {
                        "Erro ao buscar animais"
                    }
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AnimalResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 60.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyRow {
                    items(listAnimals) { animal ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row {
                                ProfileImageComponent(
                                    imageProfile = drawable.animal_default,
                                    description = "Animal",
                                    sizeImage = 250.dp,
                                    imageBorderColor = color.green_light,
                                    borderWidth = 5.dp
                                )

                                Icon(
                                    painter = painterResource(id = drawable.baseline_arrow_forward_24),
                                    contentDescription = "Icon arrow down",
                                    tint = colorResource(id = color.black),
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(20.dp)
                                )
                            }

                            Text(
                                text = animal.name,
                                color = colorResource(id = color.gray_title),
                                fontFamily = poppyns,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.offset(y = (-40).dp)
                            )

                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 48.dp)
                                    .offset(y = (-30).dp)
                            ) {
                                Row {
                                    Text(
                                        text = "Tipo:",
                                        color = colorResource(id = color.gray_title),
                                        fontFamily = poppyns,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Text(
                                        text = animal.type,
                                        color = colorResource(id = color.gray_title),
                                        fontFamily = poppyns,
                                        fontSize = 15.sp,
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Row {
                                    Text(
                                        text = "Raça:",
                                        color = colorResource(id = color.gray_title),
                                        fontFamily = poppyns,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Text(
                                        text = animal.race,
                                        color = colorResource(id = color.gray_title),
                                        fontFamily = poppyns,
                                        fontSize = 15.sp,
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Row {
                                    Text(
                                        text = "Sexo:",
                                        color = colorResource(id = color.gray_title),
                                        fontFamily = poppyns,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Text(
                                        text = animal.sex,
                                        color = colorResource(id = color.gray_title),
                                        fontFamily = poppyns,
                                        fontSize = 15.sp,
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Row {
                                    Text(
                                        text = "Idade",
                                        color = colorResource(id = color.gray_title),
                                        fontFamily = poppyns,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Text(
                                        text = "${animal.age} anos",
                                        color = colorResource(id = color.gray_title),
                                        fontFamily = poppyns,
                                        fontSize = 15.sp,
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Row {
                                    Text(
                                        text = "Descrição:",
                                        color = colorResource(id = color.gray_title),
                                        fontFamily = poppyns,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    HashtagBoxComponent(tag = animal.description, idColor = color.orange)
                                }

                                Spacer(modifier = Modifier.height(30.dp))

                                ButtonComponent(
                                    textField = "Adotar",
                                    fontTextButton = 20.sp,
                                    colorButton = color.green_light,
                                    onClick = {
                                        processService.createProcess(id = animal._id, token).enqueue(object : Callback<ProcessAd> {
                                            override fun onResponse(call: Call<ProcessAd>, response: Response<ProcessAd>) {
                                                if (response.isSuccessful) {
                                                    navController.navigate("confirmation")
                                                } else {
                                                    val errorBody = response.errorBody()?.string()
                                                    val errorMessage = if (errorBody != null) {
                                                        try {
                                                            val errorResponse =
                                                                Gson().fromJson(errorBody, ErrorResponse::class.java)
                                                            errorResponse.error


                                                        } catch (e: Exception) {
                                                            "Erro ao criar processo"
                                                        }
                                                    } else {
                                                        "Erro ao criar processo"
                                                    }
                                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                                }
                                            }

                                            override fun onFailure(call: Call<ProcessAd>, t: Throwable) {
                                                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                            }
                                        })
                                    }
                                )

//                                Row{
//                                    CircleButtonComponent(
//                                        iconImage = Icons.Default.Check,
//                                        colorButton = color.green_light,
//                                        onClick = {
//                                            navController.navigate("editAnimal")
//                                        }
//                                    )
//
//                                    Spacer(modifier = Modifier.width(50.dp))
//
//                                    CircleButtonComponent(
//                                        iconImage = Icons.AutoMirrored.Filled.ArrowForward,
//                                        colorButton = color.orange,
//                                        onClick = {
//                                            navController.navigate("editAnimal")
//                                        }
//                                    )
//                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(35.dp))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}