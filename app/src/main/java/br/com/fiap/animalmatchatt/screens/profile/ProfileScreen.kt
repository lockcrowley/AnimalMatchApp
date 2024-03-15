package br.com.fiap.animalmatchatt.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
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

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.padding(10.dp),
        ) {
            HeaderComponent()
        }

        Box(modifier = Modifier
            .padding(20.dp)
            .offset(y = (-10).dp)) {
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
                .padding(horizontal = 50.dp)
                .offset(y = (-20).dp)
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
                .padding(horizontal = 55.dp)
                .height(90.dp)
                .width(310.dp)
                .offset(y = (-10).dp)
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
                .padding(horizontal = 50.dp)
                .offset(y = (-20).dp, x = (-48).dp)
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

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row (
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ProfileImageComponent(
                    imageProfile = drawable.mini_cat,
                    description = "Olivia",
                    sizeImage = 70.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 2.dp,
                    nameProfile = "Olivia",
                    nameProfileFontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(25.dp))

                ProfileImageComponent(
                    imageProfile = drawable.mini_dog,
                    description = "Jorge",
                    sizeImage = 70.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 2.dp,
                    nameProfile = "Jorge",
                    nameProfileFontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(25.dp))

                ProfileImageComponent(
                    imageProfile = drawable.white_cat,
                    description = "Luna",
                    sizeImage = 70.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 2.dp,
                    nameProfile = "Luna",
                    nameProfileFontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row (
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ProfileImageComponent(
                    imageProfile = drawable.sausage_dog,
                    description = "Sofhi",
                    sizeImage = 70.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 2.dp,
                    nameProfile = "Sofhi",
                    nameProfileFontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(25.dp))

                ProfileImageComponent(
                    imageProfile = drawable.cat,
                    description = "Chico",
                    sizeImage = 70.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 2.dp,
                    nameProfile = "Chico",
                    nameProfileFontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(25.dp))

                ProfileImageComponent(
                    imageProfile = drawable.dog,
                    description = "Sushi",
                    sizeImage = 70.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 2.dp,
                    nameProfile = "Sushi",
                    nameProfileFontSize = 12.sp
                )
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .offset(y = (-120).dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Icon arrow back",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Icon arrow forward",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
        }
    }
}