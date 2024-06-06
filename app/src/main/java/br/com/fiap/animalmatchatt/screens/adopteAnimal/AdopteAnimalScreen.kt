package br.com.fiap.animalmatchatt.screens.adopteAnimal

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.CircleButtonComponent
import br.com.fiap.animalmatchatt.components.ProfileImageComponent

@Composable
fun AdopteAnimalScreen(navController: NavController) {
    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )
    val context = LocalContext.current.applicationContext

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
                    text = "Gatito",
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
                    horizontalAlignment = Alignment.CenterHorizontally
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
                            text = "Gato",
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
                            text = "Siames",
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
                            text = "Macho",
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
                            text = "9 anos",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 15.sp,
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        CircleButtonComponent(
                            iconImage = Icons.Default.Check,
                            colorButton = color.green_light,
                            onClick = {
                                navController.navigate("editAnimal")
                            }
                        )

                        Spacer(modifier = Modifier.width(50.dp))

                        CircleButtonComponent(
                            iconImage = Icons.AutoMirrored.Filled.ArrowForward,
                            colorButton = color.orange,
                            onClick = {
                                navController.navigate("editAnimal")
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}