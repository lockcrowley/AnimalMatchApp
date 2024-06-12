package br.com.fiap.animalmatchatt.screens.profileAdopter

import android.util.Log
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.HashtagBoxComponent
import br.com.fiap.animalmatchatt.components.ProfileImageComponent
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.UserLoginReturn
import br.com.fiap.animalmatchatt.model.UserReturn
import br.com.fiap.animalmatchatt.services.RetrofitFactory
import br.com.fiap.animalmatchatt.services.UserService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ProfileAdopterScreen(userId: String) {
    val context = LocalContext.current.applicationContext
    val retrofitFactory = RetrofitFactory()
    val userService = retrofitFactory.create(UserService::class.java)

    var user by remember { mutableStateOf<UserLoginReturn?>(null) }

    val adoptedAnimals = user?.adoptedAnimals

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    LaunchedEffect(Unit) {
        val call = userService.getUserById(id = userId)
        call.enqueue(object : Callback<UserReturn> {
            override fun onResponse(
                call: Call<UserReturn>,
                response: Response<UserReturn>
            ) {
                if (response.isSuccessful) {
                    user = response.body()?.user
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = if (errorBody != null) {
                        try {
                            val errorResponse =
                                Gson().fromJson(errorBody, ErrorResponse::class.java)
                            errorResponse.error


                        } catch (e: Exception) {
                            "Erro ao buscar usuário"
                        }
                    } else {
                        "Erro ao buscar usuário"
                    }
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserReturn>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileImageComponent(
                    imageProfile = drawable.ong_image,
                    description = "User",
                    sizeImage = 200.dp,
                    imageBorderColor = color.orange,
                    borderWidth = 5.dp
                )

                user?.let {
                    Text(
                        text = it.name,
                        color = colorResource(id = color.gray_title),
                        fontFamily = poppyns,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.offset(y = (-40).dp)
                    )
                }
//
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                        .offset(y = (-30).dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row {
                        Text(
                            text = "Logradouro:",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        user?.address?.let {
                            Text(
                                text = it.street,
                                color = colorResource(id = color.gray_title),
                                fontFamily = poppyns,
                                fontSize = 15.sp,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row {
                        Text(
                            text = "Cidade:",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        user?.address?.let {
                            Text(
                                text = it.city,
                                color = colorResource(id = color.gray_title),
                                fontFamily = poppyns,
                                fontSize = 15.sp,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row {
                        Text(
                            text = "Estado:",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        user?.address?.let {
                            Text(
                                text = it.state,
                                color = colorResource(id = color.gray_title),
                                fontFamily = poppyns,
                                fontSize = 15.sp,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row {
                        Text(
                            text = "Celular:",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        user?.let {
                            Text(
                                text = it.phone,
                                color = colorResource(id = color.gray_title),
                                fontFamily = poppyns,
                                fontSize = 15.sp,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row {
                        Text(
                            text = "Residência:",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        user?.let {
                            Text(
                                text = it.residence,
                                color = colorResource(id = color.gray_title),
                                fontFamily = poppyns,
                                fontSize = 15.sp,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row {
                        Text(
                            text = "E-mail:",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        user?.let {
                            Text(
                                text = it.email,
                                color = colorResource(id = color.gray_title),
                                fontFamily = poppyns,
                                fontSize = 15.sp,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row {
                        Text(
                            text = "Animais adotados:",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "$adoptedAnimals",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 15.sp,
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row {
                        Text(
                            text = "Quero adotar:",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        user?.let { HashtagBoxComponent(tag = it.wantToAdopt, idColor = color.green_light) }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}