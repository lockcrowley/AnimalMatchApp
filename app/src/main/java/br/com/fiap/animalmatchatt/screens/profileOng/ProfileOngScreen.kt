package br.com.fiap.animalmatchatt.screens.profileOng

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import br.com.fiap.animalmatchatt.model.UserLoginReturn
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import java.net.URLDecoder

@Composable

fun ProfileOngScreen(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val tokenManager = TokenManager(context)
    val userJson = tokenManager.getUser()
    val decodedUserJson = userJson?.let {
        URLDecoder.decode(it, "UTF-8")
    } ?: ""

    val user = Gson().fromJson(decodedUserJson, UserLoginReturn::class.java)

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

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
                            text = "Ong",
                            color = colorResource(id = color.gray_title),
                            fontFamily = poppyns,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = user.name,
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
                text = user.description!!,
                color = colorResource(id = color.gray_title),
                fontFamily = poppyns,
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .offset(y = (-30).dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box {
                Row (
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth()
                ) {
                    HashtagBoxComponent(tag = "#ONG Confiável", idColor = color.orange)
                    HashtagBoxComponent(tag = "#Cachorros", idColor = color.green_light)
                    HashtagBoxComponent(tag = "#Gatos", idColor = color.green_light)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box {
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
                        text = "Dados da Ong",
                        color = colorResource(id = color.orange),
                        fontFamily = poppyns,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            Row {
                Text(
                    text = "Logradouro:",
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = user.address.street,
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 15.sp,
                )
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

                Text(
                    text = user.address.city,
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 15.sp,
                )
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

                Text(
                    text = user.address.state,
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 15.sp,
                )
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

                Text(
                    text = user.phone,
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 15.sp,
                )
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

                Text(
                    text = user.residence,
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 15.sp,
                )
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

                Text(
                    text = user.email,
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 15.sp,
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            ButtonComponent(
                textField = "Editar Perfil",
                fontTextButton = 20.sp,
                colorButton = color.orange,
                onClick = {
                    navController.navigate("editUser")
                }
            )
        }
    }
}