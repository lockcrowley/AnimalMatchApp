package br.com.fiap.animalmatchatt.screens.confirmProcess

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun ConfirmationScreen (navController: NavController) {
    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier.weight(1f, fill = true)
        ) {
            Image(
                painter = painterResource(id = drawable.adote),
                contentDescription = "adote",
                modifier = Modifier
                    .size(280.dp)
            )
        }

        Box (
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 60.dp)
                .offset(y = (-50).dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "Parabéns!",
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Você está a poucos passos de adotar seu amiguinho!",
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Aguarde até o dono do animal entrar em contato.",
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                ButtonComponent(
                    textField = "Confirmar",
                    fontTextButton = 20.sp,
                    colorButton = color.orange,
                    onClick = {
                        navController.navigate("adoptionProcess")
                    }
                )

                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}