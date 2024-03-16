package br.com.fiap.animalmatchatt.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.animalmatchatt.R.*

@Composable
fun StatusCardComponent (
    tag: String,
    idColor: Int
) {
    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    Box(
        modifier = Modifier
            .height(18.dp)
            .width(78.dp)
            .offset(x = 18.dp)
            .background(color = colorResource(id = idColor), shape = RoundedCornerShape(3.dp))
    ){
        Text(
            text = tag,
            color = colorResource(id = color.white),
            fontFamily = poppyns,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.offset(y = (-3.5).dp).align(Alignment.Center),
        )
    }
    Spacer(modifier = Modifier.width(10.dp))
}