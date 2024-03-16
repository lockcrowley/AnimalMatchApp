package br.com.fiap.animalmatchatt.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.model.Animals

@Composable
fun ColumnListComponent (
    animals: Animals
) {
    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    Row (
        modifier = Modifier.padding(horizontal = 15.dp).offset(x = 20.dp)
    ){

        Card(
            modifier = Modifier.height(20.dp).width(85.dp)
        ) {
            Text(
                text = animals.name,
                color = colorResource(id = color.gray_title),
                fontFamily = poppyns,
                fontSize = 15.sp,
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }
        Spacer(modifier = Modifier.width(20.dp))

        Card(
            modifier = Modifier.height(20.dp).width(85.dp)
        ) {
            Text(
                text = animals.type,
                color = colorResource(id = color.gray_title),
                fontFamily = poppyns,
                fontSize = 15.sp,
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }
        Spacer(modifier = Modifier.width(20.dp))

        Card(
            modifier = Modifier.height(20.dp).width(85.dp)
        ) {
            Text(
                text = animals.race,
                color = colorResource(id = color.gray_title),
                fontFamily = poppyns,
                fontSize = 15.sp,
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Box (
            modifier = Modifier
                .background(color = colorResource(id = color.orange), shape = RoundedCornerShape(12.dp))
                .height(18.dp)
                .width(35.dp)
        ) {
            IconButton(
                modifier = Modifier.height(30.dp).width(100.dp),
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Menu Icon",
                    tint = colorResource(id = color.white),
                    modifier = Modifier.height(70.dp).width(70.dp)
                )
            }
        }
    }
}