package br.com.fiap.animalmatchatt.screens.profileAnimal

import android.net.Uri
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
import com.google.gson.Gson
import java.net.URLDecoder

@Composable
fun ProfileAnimalScreen(navController: NavController, animalJson: String) {
    val decodedUserJson = URLDecoder.decode(animalJson, "UTF-8")
    val animal = Gson().fromJson(decodedUserJson, Animal::class.java)

    val animalJsonEncode = Uri.encode(Gson().toJson(animal))

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

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
                    imageProfile = drawable.white_cat,
                    description = "Animal",
                    sizeImage = 250.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 5.dp
                )

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
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                        .offset(y = (-30).dp),
                    horizontalAlignment = Alignment.Start
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
                            text = "Idade:",
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
                        textField = "Editar dados do animal",
                        fontTextButton = 20.sp,
                        colorButton = color.orange,
                        onClick = {
                            navController.navigate("editAnimal/$animalJsonEncode")
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}