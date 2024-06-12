package br.com.fiap.animalmatchatt.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import br.com.fiap.animalmatchatt.R

@Composable
fun ButtonComponent(
    textField: String,
    fontTextButton: TextUnit,
    colorButton: Int,
    onClick: () -> Unit
) {
    val poppyns = FontFamily(
        Font(R.font.poppins_regular)
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .width(280.dp)
            .height(55.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = colorButton))
    ) {
        Text(
            text = textField,
            color = colorResource(id = R.color.white),
            fontFamily = poppyns,
            fontSize = fontTextButton,
            fontWeight = FontWeight.SemiBold
        )
    }
}
