package br.com.fiap.animalmatchatt.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.fiap.animalmatchatt.R

@Composable
fun ProfileImageComponent (
    imageProfile: Int,
    description: String,
    sizeImage: Dp,
    borderWidth: Dp,
    imageBorderColor: Int,
) {
    Box(
        modifier = Modifier
            .border(
                BorderStroke(
                    width = borderWidth,
                    color = colorResource(id = imageBorderColor)
                ),
                shape = CircleShape
            )
    ){
        Image(
            painter = painterResource(id = imageProfile),
            contentDescription = description,
            modifier = Modifier
                .size(sizeImage)
                .clip(shape = CircleShape)
        )
    }
}