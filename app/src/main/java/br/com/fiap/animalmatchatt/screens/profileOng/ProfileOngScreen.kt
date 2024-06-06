package br.com.fiap.animalmatchatt.screens.profileOng


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import br.com.fiap.animalmatchatt.components.HashtagBoxComponent
import br.com.fiap.animalmatchatt.components.ProfileImageComponent
import br.com.fiap.animalmatchatt.database.repository.AnimalRepository
import br.com.fiap.animalmatchatt.model.UserLoginReturn
import br.com.fiap.animalmatchatt.repository.getAllAnimals
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import java.net.URLDecoder

@Composable

fun ProfileOngScreen(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val tokenManager = TokenManager(context)
    val userJson = tokenManager.getUser()

    val decodedUserJson = URLDecoder.decode(userJson, "UTF-8")

    val user = Gson().fromJson(decodedUserJson, UserLoginReturn::class.java)

    val animalRepository = AnimalRepository(context)

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    var listAnimalsState = remember {
        mutableStateOf(animalRepository.listAnimal())
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 60.dp)
    ) {

        Box(modifier = Modifier
            .padding(20.dp)
            .offset(y = (-10).dp)) {
            Row () {
                ProfileImageComponent(
                    imageProfile = drawable.ong_image,
                    description = "Ong",
                    sizeImage = 100.dp,
                    imageBorderColor = color.green_light,
                    borderWidth = 4.dp
                )

                Box(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Column {
                        Text(
                            text = user.name,
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

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
                .offset(y = (-65).dp)
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
                .offset(y = (-50).dp)
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
                .offset(y = (-65).dp, x = (-48).dp)
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

            LazyColumn(
                modifier = Modifier
                    .offset(y = (-40).dp, x = (18).dp)
            ) {
                items(getAllAnimals().chunked(3)) {
                    Row (horizontalArrangement = Arrangement.SpaceAround) {
                        for (animal in it) {
                            ProfileImageComponent(
                                imageProfile = animal.imageAnimal,
                                description = animal.name,
                                sizeImage = 70.dp,
                                imageBorderColor = color.green_light,
                                borderWidth = 2.dp,
                                nameProfile = animal.name,
                                nameProfileFontSize = 12.sp
                            )

                            Spacer(modifier = Modifier.width(30.dp))
                        }
                    }
                }

                // Room DB Itens
                items(listAnimalsState.value.chunked(3)) {
                    Row (horizontalArrangement = Arrangement.SpaceAround) {
                        for (animal in it) {
                            ProfileImageComponent(
                                imageProfile = animal.imageAnimal,
                                description = animal.name,
                                sizeImage = 70.dp,
                                imageBorderColor = color.green_light,
                                borderWidth = 2.dp,
                                nameProfile = animal.name,
                                nameProfileFontSize = 12.sp
                            )

                            Spacer(modifier = Modifier.width(30.dp))
                        }
                    }
                }
            }
        }
    }

    Box (
        modifier = Modifier
            .offset(y = (620).dp, x = (145).dp)
    ) {
        Icon(
            painter = painterResource(id = drawable.baseline_arrow_downward_24),
            contentDescription = "",
            tint = colorResource(id = color.gray_title),
        )
    }

    Box (
        modifier = Modifier
            .offset(y = (620).dp, x = (245).dp)
    ) {
        Icon(
            painter = painterResource(id = drawable.baseline_arrow_downward_24),
            contentDescription = "",
            tint = colorResource(id = color.gray_title),
        )
    }
}