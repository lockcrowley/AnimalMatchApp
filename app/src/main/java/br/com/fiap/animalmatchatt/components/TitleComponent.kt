package br.com.fiap.animalmatchatt.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import br.com.fiap.animalmatchatt.R.*

@Composable
fun TitleComponent (
    title: String,
    colorText: Int,
    nameProfileFontSize: TextUnit
) {
    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    Card () {
        Text(
            text = title,
            color = colorResource(id = colorText),
            fontFamily = poppyns,
            fontSize = nameProfileFontSize,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}