package br.com.fiap.animalmatchatt.navigator

sealed class Screens (val screen: String) {

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
    data object DonationAnimalScreen: Screens("donationAnimal")
    data object AnimalRegisterScreen: Screens("animalRegister")
    data object ConfirmationScreen: Screens("confirmation")
    data object ProfileAnimalScreen: Screens("profileAnimal/{animalJson}")
    data object EditAnimalScreen: Screens("editAnimal/{animalJson}")
    data object PasswordChangeScreen: Screens("changePassword")
    data object AdoptAnimalScreen: Screens("adoptAnimal")
    data object ProfileAdopterScreen: Screens("adopter/{userId}")
    data object ConfirmToConcludedScreen: Screens("concluded/{processId}")
}