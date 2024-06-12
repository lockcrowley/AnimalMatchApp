package br.com.fiap.animalmatchatt.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.model.ProcessAd

@Composable
fun ColumnProcessListComponent (
    process: ProcessAd
) {
    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    var colorStatus by remember {
        mutableStateOf(color.gray_title)
    }

    if (process.status == "Concluido") {
        colorStatus = color.green_light
    } else if (process.status == "Pendente") {
        colorStatus = color.orange
    }

    Row (
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .offset(x = 20.dp)
    ){

        Card(
            modifier = Modifier
                .height(20.dp)
                .width(85.dp)
                .offset(x = (-8).dp)
        ) {
            Text(
                text = process.animalName,
                color = colorResource(id = color.gray_title),
                fontFamily = poppyns,
                fontSize = 15.sp,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.width(15.dp))

        Card(
            modifier = Modifier
                .height(20.dp)
                .width(85.dp)
        ) {
            Text(
                text = "${process.days} dias",
                color = colorResource(id = color.gray_title),
                fontFamily = poppyns,
                fontSize = 15.sp,
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }
        Spacer(modifier = Modifier.width(15.dp))

        StatusCardComponent(
            tag = process.status,
            idColor = colorStatus
        )
    }
}