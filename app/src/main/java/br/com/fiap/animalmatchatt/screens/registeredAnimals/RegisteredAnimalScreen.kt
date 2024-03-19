package br.com.fiap.animalmatchatt.screens.registeredAnimals

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.ColumnRegisterListComponent
import br.com.fiap.animalmatchatt.components.HeaderComponent
import br.com.fiap.animalmatchatt.components.TitleComponent
import br.com.fiap.animalmatchatt.database.repository.AnimalRepository
import br.com.fiap.animalmatchatt.repository.getAllAnimalsBySearch

@Composable
fun RegisteredAnimalScreen () {

    var searchTextState by remember {
        mutableStateOf("")
    }

    var listAnimals by remember {
        mutableStateOf(getAllAnimalsBySearch(searchTextState))
    }

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    val context = LocalContext.current
    val animalRepository = AnimalRepository(context)

    var listAnimalsState = remember {
        mutableStateOf(animalRepository.listAnimal())
    }

    Column (
        modifier = Modifier.padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ){
            TitleComponent(
                title = "Cadastros",
                colorText = color.green_light,
                nameProfileFontSize = 25.sp
            )
        }
        
        Spacer(modifier = Modifier.height(25.dp))

        Box{
            OutlinedTextField(
                value = searchTextState,
                onValueChange = {
                    searchTextState = it
                    listAnimals = getAllAnimalsBySearch(searchTextState)
                },
                modifier = Modifier
                    .width(330.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorResource(id = color.green_light),
                    focusedBorderColor = colorResource(id = color.green_light)
                ),
                placeholder = {
                    Text(
                        text = "Ache seu pet",
                        fontFamily = poppyns,
                        color = colorResource(id = color.gray_title)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = colorResource(id = color.gray_title)
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Column {
            Row(
                modifier = Modifier.padding(horizontal = 55.dp)
            ) {
                TitleComponent(
                    title = "Nome",
                    colorText = color.green_light,
                    nameProfileFontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(52.dp))

                TitleComponent(
                    title = "Tipo",
                    colorText = color.green_light,
                    nameProfileFontSize = 20.sp
                )

                Spacer(modifier = Modifier.width(52.dp))

                TitleComponent(
                    title = "Raça",
                    colorText = color.green_light,
                    nameProfileFontSize = 20.sp
                )
            }

            Row {
                LazyColumn(modifier = Modifier.fillMaxWidth().padding(16.dp),
                    ) {
                    items(listAnimals) {
                        ColumnRegisterListComponent(
                            animals = it
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    items(listAnimalsState.value) {
                        ColumnRegisterListComponent(
                            animals = it
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }

    Canvas(
        modifier = Modifier.height(10.dp).width(10.dp).offset(x = (139).dp, y = 230.dp)
    ) {
        drawLine(
            color = Color.Green,
            start = Offset(size.width/ 2, 10f),
            end = Offset(size.width / 2, 999f),
            strokeWidth = 1.2f,
            cap = StrokeCap.Round,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
        )
    }

    Canvas(
        modifier = Modifier.height(10.dp).width(10.dp).offset(x = 237.dp, y = 230.dp)
    ) {
        drawLine(
            color = Color.Green,
            start = Offset(size.width/ 2, 10f),
            end = Offset(size.width / 2, 999f),
            strokeWidth = 1.2f,
            cap = StrokeCap.Round,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
        )
    }
}