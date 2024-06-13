package br.com.fiap.animalmatchatt.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.animalmatchatt.screens.loading.LoadingScreen
import br.com.fiap.animalmatchatt.utils.TokenManager
import kotlinx.coroutines.delay

@Composable
fun CentralNavigation(navController: NavHostController) {
    val context = LocalContext.current.applicationContext
    val tokenManager = TokenManager(context)

    var initialDestination by remember {
        mutableStateOf("loading")
    }

    var token by remember { mutableStateOf<String?>(null) }
    var userJson by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = Unit) {
        delay(1000)

        token = tokenManager.getAccessToken()
        if (token == null) {
            initialDestination = Screens.AuthNavScreen.screen
        } else {
            if (!tokenManager.isTokenExpired(token!!)) {
                userJson = tokenManager.getUser()
                if (userJson != null) {
                    initialDestination = Screens.DrawerScreen.screen
                } else {
                    initialDestination = Screens.AuthNavScreen.screen
                }
            } else {
                initialDestination = Screens.AuthNavScreen.screen
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = initialDestination,
    ) {
        composable(Screens.LoadingScreen.screen) {
            LoadingScreen()
        }
        composable(Screens.AuthNavScreen.screen) {
            NavigationAuthController()
        }
        composable(Screens.DrawerScreen.screen) {
            NavigationDrawerController()
        }
    }
}