package br.com.fiap.animalmatchatt.navigator

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.HeaderComponent
import br.com.fiap.animalmatchatt.screens.adoptionProcess.AdoptionProcessScreen
import br.com.fiap.animalmatchatt.screens.animalRegister.AnimalRegisterScreen
import br.com.fiap.animalmatchatt.screens.profile.ProfileScreen
import br.com.fiap.animalmatchatt.screens.registeredAnimals.RegisteredAnimalScreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationController () {
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current.applicationContext

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ){
                    Text(text = "")
                }
                HorizontalDivider()
                //PROFILE
                NavigationDrawerItem(
                    label = { Text(text = "Perfil") },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.baseline_person_outline_24),
                            contentDescription = "",
                            tint = colorResource(id = color.green_light)
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.ProfileScreen.screen) {
                            popUpTo(0)
                        }
                    }
                )
                //REGISTERED ANIMALS
                NavigationDrawerItem(
                    label = { Text(text = "Animais") },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.baseline_pets_24),
                            contentDescription = "",
                            tint = colorResource(id = color.green_light)
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.RegisteredAnimalScreen.screen) {
                            popUpTo(0)
                        }
                    }
                )
                //ADOPTION PROCESS
                NavigationDrawerItem(
                    label = { Text(text = "Processo") },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.dog_resting_on_a_pet_hotel_bed_svgrepo_com),
                            contentDescription = "",
                            tint = colorResource(id = color.green_light)
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.AdoptionProcessScreen.screen) {
                            popUpTo(0)
                        }
                    }
                )
                //ANIMAL REGISTER
                NavigationDrawerItem(
                    label = { Text(text = "Cadastrar") },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.sharp_pet_supplies_24),
                            contentDescription = "",
                            tint = colorResource(id = color.green_light)
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.AnimalRegisterScreen.screen) {
                            popUpTo(0)
                        }
                    }
                )

                //LOGOUT
                NavigationDrawerItem(
                    label = { Text(text = "Sair") },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.baseline_logout_24),
                            contentDescription = "",
                            tint = colorResource(id = color.green_light)
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }) {
        Scaffold(
            topBar = {
                val coroutineScope = rememberCoroutineScope()

                HeaderComponent{
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
            }
        ) {
            NavHost(
                navController = navigationController,
                startDestination = Screens.ProfileScreen.screen,
            ) {
                composable(Screens.ProfileScreen.screen){ ProfileScreen()}
                composable(Screens.RegisteredAnimalScreen.screen){ RegisteredAnimalScreen() }
                composable(Screens.AdoptionProcessScreen.screen){ AdoptionProcessScreen() }
                composable(Screens.AnimalRegisterScreen.screen){ AnimalRegisterScreen() }
            }
        }
    }

}