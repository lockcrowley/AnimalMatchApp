package br.com.fiap.animalmatchatt.navigator

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.animalmatchatt.screens.registerUsers.RegisterUsersScreen
import br.com.fiap.animalmatchatt.screens.userLogin.LoginScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationAuthController() {
    val navController = rememberNavController()

    Scaffold() {
        NavHost(
            navController = navController,
            startDestination = Screens.LoginUserScreen.screen,
            exitTransition = {
                slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.End)
            }
        ) {
            composable(Screens.RegisterUserScreen.screen){ RegisterUsersScreen(navController) }
            composable(Screens.LoginUserScreen.screen){ LoginScreen(navController) }
            composable(Screens.ProfileScreen.screen){ NavigationController() }
        }
    }
}