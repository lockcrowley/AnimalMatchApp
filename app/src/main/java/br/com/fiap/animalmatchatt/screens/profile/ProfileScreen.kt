package br.com.fiap.animalmatchatt.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.fiap.animalmatchatt.R
import br.com.fiap.animalmatchatt.components.HeaderComponent

@Composable

fun ProfileScreen() {
    Column {
        Box(
            modifier = Modifier.padding(10.dp)
        ) {
            HeaderComponent()
        }

        Box(modifier = Modifier.padding(40.dp)) {
            Row () {
                Box(
                    modifier = Modifier
                        .border(
                            BorderStroke(
                                width = 4.dp,
                                color = colorResource(id = R.color.green_light)
                            ),
                            shape = CircleShape
                        )
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ong_image),
                        contentDescription = "ong",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(shape = CircleShape)
                    )
                }
            }
        }
    }
}