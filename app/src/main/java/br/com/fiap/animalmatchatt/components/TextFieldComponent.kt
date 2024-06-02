package br.com.fiap.animalmatchatt.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import br.com.fiap.animalmatchatt.R.*

@Composable
fun TextFieldComponent(
    fieldValue: String,
    onFieldChange: (String) -> Unit,
    placeholderValue: String,
    iconImage: Painter,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    Card (
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        OutlinedTextField(
            value = fieldValue,
            onValueChange = {
                onFieldChange(it)
            },
            modifier = Modifier
                .width(330.dp)
                .height(55.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = colorResource(id = color.white),
                focusedBorderColor = colorResource(id = color.white)
            ),
            placeholder = {
                Text(
                    text = placeholderValue,
                    fontFamily = poppyns,
                    color = colorResource(id = color.gray_title)
                )
            },
            leadingIcon = {
                Icon(
                    painter = iconImage,
                    contentDescription = "",
                    tint = colorResource(id = color.green_light)
                )
            },
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation
        )
    }
}