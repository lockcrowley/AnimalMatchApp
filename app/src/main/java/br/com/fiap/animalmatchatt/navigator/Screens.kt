package br.com.fiap.animalmatchatt.navigator

sealed class Screens (val screen: String) {

    data object CentralNavScreen: Screens("central_navigation")
    data object LoadingScreen: Screens("loading")
    data object AuthNavScreen: Screens("auth")
    data object DrawerScreen: Screens("drawer")
    data object RegisterUserScreen: Screens("registerUser")
    data object LoginUserScreen: Screens("login")
    data object ForgotPasswordScreen: Screens("forgot")
    data object ResetPasswordScreen: Screens("reset")
    data object ProfileUserScreen: Screens("profileUser")
    data object ProfileOngScreen: Screens("profileOng")
    data object EditScreen: Screens("editUser")
    data object RegisteredAnimalScreen: Screens("registeredAnimals")
    data object AdoptionProcessScreen: Screens("adoptionProcess")
    data object AnimalRegisterScreen: Screens("animalRegister")
    data object ConfirmationScreen: Screens("confirmation")
    data object ProfileAnimalScreen: Screens("profileAnimal")

    data object EditAnimalScreen: Screens("editAnimal")

    data object AdopteAnimalScreen: Screens("adopteAnimal")
}