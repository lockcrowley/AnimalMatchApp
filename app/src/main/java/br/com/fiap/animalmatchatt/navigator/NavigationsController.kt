package br.com.fiap.animalmatchatt.navigator

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    var screenWidth = LocalConfiguration.current.screenWidthDp.dp
    screenWidth /= 2

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet (
                modifier =  Modifier.width(screenWidth)
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                Box (
                    modifier = Modifier
                        .fillMaxWidth().padding(horizontal = 5.dp)
                ){
                    Row () {
                        Text(
                            text = "Bem vinda, ",
                            color = colorResource(id = color.orange),
                            fontFamily = poppyns,
                            fontSize = 18.sp
                        )

                        Text(
                            text = "ONG",
                            color = colorResource(id = color.orange),
                            fontFamily = poppyns,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    }
                }

                Box (modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 5.dp)) {
                    Text(
                        text = "Pata Voluntária" ,
                        color = colorResource(id = color.orange),
                        fontFamily = poppyns,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }

                HorizontalDivider()
                //PROFILE
                NavigationDrawerItem(
                    label = { Text(
                        text = "Perfil",
                        fontFamily = poppyns,
                        modifier = Modifier
                            .offset(x = (-15).dp)
                    ) },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.baseline_person_outline_24),
                            contentDescription = "",
                            tint = colorResource(id = color.green_light),
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .offset(x = (-10).dp)
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
                    label = { Text(text = "Animais", fontFamily = poppyns, modifier = Modifier
                        .offset(x = (-15).dp)) },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.baseline_pets_24),
                            contentDescription = "",
                            tint = colorResource(id = color.green_light),
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .offset(x = (-10).dp)
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
                    label = { Text(text = "Processo de adoção", fontFamily = poppyns, modifier = Modifier
                        .offset(x = (-15).dp)) },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.dog_resting_on_a_pet_hotel_bed_svgrepo_com),
                            contentDescription = "",
                            tint = colorResource(id = color.green_light),
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .offset(x = (-10).dp)
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
                    label = { Text(text = "Cadastrar", fontFamily = poppyns, modifier = Modifier
                        .offset(x = (-15).dp)) },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.sharp_pet_supplies_24),
                            contentDescription = "",
                            tint = colorResource(id = color.green_light),
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .offset(x = (-10).dp)
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

                Spacer(modifier = Modifier.height(300.dp))

                //LOGOUT
                NavigationDrawerItem(
                    label = { Text(text = "Sair", fontFamily = poppyns, modifier = Modifier
                        .offset(x = (-15).dp)) },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.baseline_logout_24),
                            contentDescription = "",
                            tint = colorResource(id = color.green_light),
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .offset(x = (-10).dp)
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