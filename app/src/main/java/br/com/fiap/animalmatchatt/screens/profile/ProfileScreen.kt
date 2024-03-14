package br.com.fiap.animalmatchatt.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.HashtagBoxComponent
import br.com.fiap.animalmatchatt.components.HeaderComponent
import br.com.fiap.animalmatchatt.components.ProfileImageComponent

@Composable

fun ProfileScreen() {
    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    Column {
        Box(
            modifier = Modifier.padding(10.dp)
        ) {
            HeaderComponent()
        }

        Box(modifier = Modifier.padding(40.dp)) {
            Row () {
                ProfileImageComponent(
                    imageProfile = drawable.ong_image,
                    description = "Ong",
                    sizeImage = 100.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 4.dp,
                )

                Box(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Column {
                        Text(
                            text = "ONG",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Text(
                            text = "Pata Voluntaria",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.offset(y = (-20).dp)
                        )
                    }
                }
            }
        }

        Box (
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .offset(y = (-35).dp)
        ) {
            Text(
                text = "A ONG Pata Voluntária é um grupo dedicado de pessoas que se unem para resgatar e proteger animais em situação de vulnerabilidade.",
                color = colorResource(id = color.gray_title),
                fontFamily = poppyns,
                fontSize = 12.sp
            )
        }

        Box (
            modifier = Modifier
                .padding(horizontal = 45.dp)
                .height(90.dp)
                .width(310.dp)
                .offset(y = (-20).dp)
        ) {
            Row (
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
            ) {
                Column {
                    Row {
                        HashtagBoxComponent(tag = "#ONG Confiável", idColor = color.orange)
                        HashtagBoxComponent(tag = "#Mais de 100 doações", idColor = color.orange)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        HashtagBoxComponent(tag = "#1 ano app", idColor = color.orange)
                        HashtagBoxComponent(tag = "#Cachorros", idColor = color.green_light)
                        HashtagBoxComponent(tag = "#Gatos", idColor = color.green_light)
                    }
                }
            }
        }

        Box (
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .offset(y = (-15).dp)
        ) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = drawable.baseline_arrow_drop_down_circle_24),
                    contentDescription = "Icon arrow down",
                    tint = colorResource(id = color.orange),
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                )

                Text(
                    text = "Animais em adoção",
                    color = colorResource(id = color.orange),
                    fontFamily = poppyns,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        Box (
            modifier = Modifier
                .padding(horizontal = 40.dp)
        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ProfileImageComponent(
                    imageProfile = drawable.mini_cat,
                    description = "Olivia",
                    sizeImage = 60.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 2.dp,
                )

                ProfileImageComponent(
                    imageProfile = drawable.mini_dog,
                    description = "Jorge",
                    sizeImage = 60.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 2.dp,
                )

                ProfileImageComponent(
                    imageProfile = drawable.white_cat,
                    description = "Luna",
                    sizeImage = 60.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 2.dp,
                )
            }
        }
    }
}