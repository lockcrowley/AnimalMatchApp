package br.com.fiap.animalmatchatt.navigator

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.HeaderComponent
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.UserLoginReturn
import br.com.fiap.animalmatchatt.screens.adoptAnimal.AdoptAnimalScreen
import br.com.fiap.animalmatchatt.screens.adoptionProcess.AdoptionProcessScreen
import br.com.fiap.animalmatchatt.screens.animalRegister.AnimalRegisterScreen
import br.com.fiap.animalmatchatt.screens.changePassword.ChangePasswordScreen
import br.com.fiap.animalmatchatt.screens.confirmAdoptionConcluded.ConfirmAdoptionToConcludedScreen
import br.com.fiap.animalmatchatt.screens.confirmProcess.ConfirmationScreen
import br.com.fiap.animalmatchatt.screens.donationProcess.DonationProcessScreen
import br.com.fiap.animalmatchatt.screens.editAnimal.EditAnimalScreen
import br.com.fiap.animalmatchatt.screens.editProfile.EditProfileScreen
import br.com.fiap.animalmatchatt.screens.profileAdopter.ProfileAdopterScreen
import br.com.fiap.animalmatchatt.screens.profileAnimal.ProfileAnimalScreen
import br.com.fiap.animalmatchatt.screens.profileOng.ProfileOngScreen
import br.com.fiap.animalmatchatt.screens.profileUser.ProfileUserScreen
import br.com.fiap.animalmatchatt.screens.registeredAnimals.RegisteredAnimalScreen
import br.com.fiap.animalmatchatt.screens.userLogin.LoginScreen
import br.com.fiap.animalmatchatt.services.AuthService
import br.com.fiap.animalmatchatt.services.RetrofitFactory
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLDecoder
import kotlin.system.exitProcess

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawerController () {
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current.applicationContext
    val activity = (context as? Activity)
    val retrofitFactory = RetrofitFactory()
    val authService = retrofitFactory.create(AuthService::class.java)
    val tokenManager = TokenManager(context)

    var userJson by remember { mutableStateOf<String?>(null) }

    userJson = tokenManager.getUser()

    val decodedUserJson = URLDecoder.decode(userJson, "UTF-8")
    val user = Gson().fromJson(decodedUserJson, UserLoginReturn::class.java)
    var screenWidth = LocalConfiguration.current.screenWidthDp.dp
    screenWidth /= 2

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    val initialDestination = if (user.isOng == true) {
        "profileOng"
    } else {
        "profileUser"
    }

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
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                ){
                    Column {
                        Row () {
                            Text(
                                text = "Bem vindo(a),",
                                color = colorResource(id = color.orange),
                                fontFamily = poppyns,
                                fontSize = 18.sp
                            )

                            if(user.isOng == true) {
                                Text(
                                    text = "Ong",
                                    color = colorResource(id = color.orange),
                                    fontFamily = poppyns,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                )
                            }
                        }

                        Box (modifier = Modifier
                            .fillMaxWidth()
                        ) {
                            Text(
                                text = user.name,
                                color = colorResource(id = color.orange),
                                fontFamily = poppyns,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                            )
                        }
                    }
                }

                HorizontalDivider()
                //HOME
                NavigationDrawerItem(
                    label = { Text(
                        text = "Adote um animal",
                        fontFamily = poppyns,
                        modifier = Modifier
                            .offset(x = (-15).dp)
                    ) },
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

                        navigationController.navigate("adoptAnimal") {
                            popUpTo(0)
                        }
                    }
                )
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

                        navigationController.navigate(initialDestination) {
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
                //DONATION PROCESS
                NavigationDrawerItem(
                    label = { Text(text = "Processo de doação", fontFamily = poppyns, modifier = Modifier
                        .offset(x = (-15).dp)) },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.baseline_type_animal_24),
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
                        navigationController.navigate(Screens.DonationAnimalScreen.screen) {
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
                //CHANGE PASSWORD
                NavigationDrawerItem(
                    label = { Text(text = "Alterar senha", fontFamily = poppyns, modifier = Modifier
                        .offset(x = (-15).dp)) },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = drawable.baseline_lock_person_24),
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
                        navigationController.navigate(Screens.PasswordChangeScreen.screen) {
                            popUpTo(0)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(170.dp))

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
                        authService.userLogout().enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    tokenManager.clearUser()
                                    tokenManager.clearTokens()
                                    Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()

                                    Handler(Looper.getMainLooper()).postDelayed({
                                        activity?.finishAffinity()
                                        exitProcess(0)
                                    }, 1000)
                                }  else {
                                    val errorBody = response.errorBody()?.string()
                                    val errorMessage = if (errorBody != null) {
                                        try {
                                            val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                                            errorResponse.error
                                        } catch (e: Exception) {
                                            "Erro ao tentar deslogar, tente novamente!"
                                        }
                                    } else {
                                        "Erro ao tentar deslogar, tente novamente!"
                                    }
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                            }
                        })

                        coroutineScope.launch {
                            drawerState.close()
                        }
                    }
                )
            }
        }) {
        Scaffold(
            topBar = {
                HeaderComponent{
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
            }
        ) {
            NavHost(
                navController = navigationController,
                startDestination = "adoptAnimal",
            ) {
                composable(Screens.ProfileUserScreen.screen){ ProfileUserScreen(navigationController) }
                composable(Screens.ProfileOngScreen.screen){ ProfileOngScreen(navigationController) }
                composable(Screens.EditScreen.screen){ EditProfileScreen(navigationController) }
                composable(Screens.RegisteredAnimalScreen.screen){ RegisteredAnimalScreen(navigationController) }
                composable(Screens.AdoptionProcessScreen.screen){ AdoptionProcessScreen(navigationController) }
                composable(Screens.DonationAnimalScreen.screen){ DonationProcessScreen(navigationController) }
                composable(Screens.AnimalRegisterScreen.screen){ AnimalRegisterScreen(navigationController) }
                composable(Screens.ConfirmationScreen.screen) { ConfirmationScreen(navigationController) }
                composable(Screens.PasswordChangeScreen.screen) { ChangePasswordScreen() }
                composable(
                    Screens.EditAnimalScreen.screen,
                    arguments = listOf(
                        navArgument(name = "animalJson") {
                            type = NavType.StringType
                        })
                ) {
                    val animal = it.arguments?.getString("animalJson") ?: ""
                    EditAnimalScreen(navigationController, animal)
                }
                composable(
                    Screens.ProfileAnimalScreen.screen,
                    arguments = listOf(
                        navArgument(name = "animalJson") {
                            type = NavType.StringType
                    })
                ) {
                    val animal = it.arguments?.getString("animalJson") ?: ""
                    ProfileAnimalScreen(navigationController, animal)
                }
                composable(
                    Screens.ProfileAdopterScreen.screen,
                    arguments = listOf(
                        navArgument(name = "userId") {
                            type = NavType.StringType
                        })
                ) {
                    val userId = it.arguments?.getString("userId") ?: ""
                    ProfileAdopterScreen(userId)
                }
                composable(
                    Screens.ConfirmToConcludedScreen.screen,
                    arguments = listOf(
                        navArgument(name = "processId") {
                            type = NavType.StringType
                        })
                ) {
                    val processId = it.arguments?.getString("processId") ?: ""
                    ConfirmAdoptionToConcludedScreen(processId)
                }

                composable(Screens.AdoptAnimalScreen.screen){ AdoptAnimalScreen(navigationController) }

                composable(Screens.LoginUserScreen.screen){ LoginScreen(navigationController) }
            }
        }
    }

}