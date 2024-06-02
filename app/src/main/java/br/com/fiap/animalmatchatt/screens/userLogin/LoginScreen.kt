package br.com.fiap.animalmatchatt.screens.userLogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.HeaderAuthComponent

@Composable
fun LoginScreen (navController: NavController) {

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderAuthComponent(navController, loginScreen = true)

        Box() {
            Image(
                painter = painterResource(id = drawable.dog),
                contentDescription = "login",
                modifier = Modifier
                    .size(10.dp)
            )
        }
    }
}