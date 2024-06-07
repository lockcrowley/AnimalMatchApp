package br.com.fiap.animalmatchatt.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.model.Animal
import com.google.gson.Gson

@Composable
fun ColumnRegisterListComponent (
    animal: Animal,
    navController: NavController
) {

    val animalJson = Uri.encode(Gson().toJson(animal))
    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    Row (
        modifier = Modifier.padding(horizontal = 15.dp).offset(x = 15.dp)
    ){

        Card(
            modifier = Modifier.height(20.dp).width(85.dp)
        ) {
            Text(
                text = animal.name,
                color = colorResource(id = color.gray_title),
                fontFamily = poppyns,
                fontSize = 15.sp,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.width(20.dp))

        Card(
            modifier = Modifier.height(20.dp).width(85.dp)
        ) {
            Text(
                text = animal.type,
                color = colorResource(id = color.gray_title),
                fontFamily = poppyns,
                fontSize = 15.sp,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.width(20.dp))

        Card(
            modifier = Modifier.height(20.dp).width(85.dp).offset(x = (-8).dp)
        ) {
            Text(
                text = animal.race,
                color = colorResource(id = color.gray_title),
                fontFamily = poppyns,
                fontSize = 15.sp,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Box (
            modifier = Modifier
                .background(color = colorResource(id = color.orange), shape = RoundedCornerShape(12.dp))
                .height(18.dp)
                .width(40.dp)
        ) {
            IconButton(
                modifier = Modifier.height(30.dp).width(100.dp),
                onClick = {
                    navController.navigate("profileAnimal/$animalJson")
                },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Menu Icon",
                    tint = colorResource(id = color.white),
                    modifier = Modifier.height(70.dp).width(80.dp)
                )
            }
        }
    }
}