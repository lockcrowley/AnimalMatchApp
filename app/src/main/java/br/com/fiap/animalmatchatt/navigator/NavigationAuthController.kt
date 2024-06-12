package br.com.fiap.animalmatchatt.navigator


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.animalmatchatt.screens.forgotPassword.ForgotPasswordScreen
import br.com.fiap.animalmatchatt.screens.registerUsers.RegisterUsersScreen
import br.com.fiap.animalmatchatt.screens.resetPassword.ResetPasswordScreen
import br.com.fiap.animalmatchatt.screens.userLogin.LoginScreen

@SuppressLint("SuspiciousIndentation")
@Composable
fun NavigationAuthController() {
    val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "login",
        ) {
            composable(Screens.LoginUserScreen.screen){ LoginScreen(navController) }
            composable(Screens.RegisterUserScreen.screen){ RegisterUsersScreen(navController) }
            composable(Screens.DrawerScreen.screen) { NavigationDrawerController() }
            composable(Screens.ForgotPasswordScreen.screen) { ForgotPasswordScreen(navController) }
            composable(Screens.ResetPasswordScreen.screen) { ResetPasswordScreen(navController) }
        }
}