package br.com.fiap.animalmatchatt.repository

import br.com.fiap.animalmatchatt.model.Animals

fun getAllAnimals(): List<Animals> {

    return listOf(
        Animals(id = 1, name = "Penelope", type = "Gato", race = "Siamês"),
        Animals(id = 2, name = "Jorge", type = "Cachorro", race = "Pug"),
        Animals(id = 3, name = "Flash", type = "Cachorro", race = "Poodle"),
        Animals(id = 4, name = "Thor", type = "Cachorro", race = "Labrador"),
        Animals(id = 5, name = "Olivia", type = "Gato", race = "Persa"),
        Animals(id = 6, name = "Panqueca", type = "Cachorro", race = "Buldogue"),
        Animals(id = 7, name = "Chico", type = "Gato", race = "Bombaim"),
        Animals(id = 8, name = "Luna", type = "Gato", race = "Siamês"),
        Animals(id = 9, name = "Sushi", type = "Cachorro", race = "Vira-lata"),
        Animals(id = 10, name = "Floquinho", type = "Cachorro", race = "Vira-lata")
    )
}

fun getAllAnimalsBySearch(name: String): List<Animals>{
    return getAllAnimals().filter {
        it.name.startsWith(prefix = name, ignoreCase = true)
//        it.type.startsWith(prefix = type, ignoreCase = true)
//        it.race.startsWith(prefix = race, ignoreCase = true)
    }
}