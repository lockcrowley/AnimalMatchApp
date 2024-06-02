package br.com.fiap.animalmatchatt.navigator

sealed class Screens (val screen: String) {

    data object RegisterUserScreen: Screens("registerUser")
    data object LoginUserScreen: Screens("login")
    data object ProfileScreen: Screens("profile")
    data object RegisteredAnimalScreen: Screens("registeredAnimals")
    data object AdoptionProcessScreen: Screens("adoptionProcess")
    data object AnimalRegisterScreen: Screens("animalRegister")
}