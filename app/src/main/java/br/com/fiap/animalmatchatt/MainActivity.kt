package br.com.fiap.animalmatchatt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.animalmatchatt.screens.adoptionProcess.AdoptionProcess
import br.com.fiap.animalmatchatt.screens.animalRegister.AnimalRegisterScreen
import br.com.fiap.animalmatchatt.screens.profile.ProfileScreen
import br.com.fiap.animalmatchatt.screens.registeredAnimals.RegisteredAnimalScreen
import br.com.fiap.animalmatchatt.ui.theme.AnimalMatchAttTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalMatchAttTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    ProfileScreen()
//                    RegisteredAnimalScreen()
//                    AdoptionProcess()
                    AnimalRegisterScreen()
                }
            }
        }
    }
}