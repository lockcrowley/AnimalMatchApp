package br.com.fiap.animalmatchatt.screens.profileUser

import android.util.Log
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import br.com.fiap.animalmatchatt.components.HeaderComponent
import br.com.fiap.animalmatchatt.components.ProfileImageComponent
import br.com.fiap.animalmatchatt.database.repository.AnimalRepository
import br.com.fiap.animalmatchatt.model.UserLoginReturn
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.URLDecoder

@Composable
fun ProfileUserScreen(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val tokenManager = TokenManager(context)
    val userJson = tokenManager.getUser()
    val decodedUserJson = userJson?.let {
        URLDecoder.decode(it, "UTF-8")
    } ?: ""
    val user = Gson().fromJson(decodedUserJson, UserLoginReturn::class.java)
    val adoptedAnimals = user?.adoptedAnimals

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
                    imageProfile = drawable.ong_image,
                    description = "User",
                    sizeImage = 200.dp,
                    imageBorderColor = color.orange,
                    borderWidth = 5.dp
                )

                Text(
                    text = user.name,
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

                        HashtagBoxComponent(tag = user.wantToAdopt, idColor = color.green_light)
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

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}