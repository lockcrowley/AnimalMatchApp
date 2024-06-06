package br.com.fiap.animalmatchatt.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.animalmatchatt.R
import br.com.fiap.animalmatchatt.navigator.Screens

@Composable
fun HeaderAuthComponent(
    navController: NavController,
    icon: Boolean
) {
    val poppyns = FontFamily(
        Font(R.font.poppins_regular)
    )
    Box(contentAlignment = Alignment.CenterStart) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                if(icon) {
                    IconButton(
                        onClick = {
                            navController.navigate("login")
                        },
                        modifier = Modifier.offset(y = (-3).dp, x = (-15).dp).width(95.dp).height(50.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow Back Icon",
                            tint = colorResource(id = R.color.green_light),
                            modifier = Modifier
                                .width(100.dp)
                                .height(55.dp)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(85.dp))
                }
                Spacer(modifier = Modifier.width(25.dp))

                Text(
                    text = "Animal",
                    color = colorResource(id = R.color.logo),
                    fontFamily = poppyns,
                    fontSize = 18.sp
                )
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(36.dp)
                )
                Text(
                    text = "Match",
                    color = colorResource(id = R.color.logo),
                    fontFamily = poppyns,
                    fontSize = 18.sp
                )
            }
        }
    }

}