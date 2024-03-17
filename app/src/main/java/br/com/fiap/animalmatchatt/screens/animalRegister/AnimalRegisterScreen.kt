package br.com.fiap.animalmatchatt.screens.animalRegister

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.ButtonComponent
import br.com.fiap.animalmatchatt.components.HeaderComponent
import br.com.fiap.animalmatchatt.components.TextFieldComponent
import br.com.fiap.animalmatchatt.components.TitleComponent

@Composable
fun AnimalRegisterScreen() {

    var animalName by remember() {
        mutableStateOf("")
    }

    var animalType by remember {
        mutableStateOf("")
    }

    var animalRace by remember {
        mutableStateOf("")
    }


    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            TitleComponent(
                title = "Cadastre seu animal",
                colorText = color.gray_title,
                nameProfileFontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(colorResource(id = color.green_bold)),
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                TextFieldComponent(
                    fieldValue = animalName,
                    placeholderValue =  "Nome do pet",
                    iconImage = painterResource(id = drawable.baseline_pets_24)
                )

                Spacer(modifier = Modifier.height(30.dp))

                TextFieldComponent(
                    fieldValue = animalType,
                    placeholderValue =  "Tipo do pet",
                    iconImage = painterResource(id = drawable.baseline_type_animal_24)
                )

                Spacer(modifier = Modifier.height(30.dp))

                TextFieldComponent(
                    fieldValue = animalRace,
                    placeholderValue =  "Raça do pet",
                    iconImage = painterResource(id = drawable.sharp_pet_supplies_24)
                )

                Spacer(modifier = Modifier.height(70.dp))

                ButtonComponent(
                    textField = "Cadastrar",
                    fontTextButton = 20.sp,
                    colorButton = color.orange
                )
            }
        }
    }
}

