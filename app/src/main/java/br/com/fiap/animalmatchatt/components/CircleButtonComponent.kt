package br.com.fiap.animalmatchatt.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp


@Composable
fun CircleButtonComponent(
    iconImage: ImageVector,
    colorButton: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(78.dp)
            .height(55.dp),
        shape = RoundedCornerShape(27.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = colorButton))
    ) {
        Icon(
            iconImage,
            contentDescription = "Favorite",
            modifier = Modifier.size(60.dp)
            .scale(1.5f)
        )
    }
}
