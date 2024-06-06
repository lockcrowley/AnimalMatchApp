package br.com.fiap.animalmatchatt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.fiap.animalmatchatt.navigator.CentralNavigation

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
                    CentralNavigation(navController = rememberNavController())
                }
            }
        }
    }
}