package br.com.fiap.animalmatchatt.repository

import br.com.fiap.animalmatchatt.model.Animals
import br.com.fiap.animalmatchatt.R.*

fun getAllAnimals(): List<Animals> {

    return listOf(
        Animals(id = 1, name = "Penelope", type = "Gato", race = "Siamês", time = "2 dias", status = "Aprovado", drawable.white_cat),
        Animals(id = 2, name = "Jorge", type = "Cachorro", race = "Pug", time = "6 dias", status = "Pendente", imageAnimal = drawable.mini_dog),
        Animals(id = 4, name = "Thor", type = "Cachorro", race = "Labrador", time = "12 dias", status = "Cancelado", imageAnimal = drawable.dog),
        Animals(id = 5, name = "Olivia", type = "Gato", race = "Persa", time = "12 dias", status = "Pendente", imageAnimal = drawable.mini_cat),
        Animals(id = 6, name = "Panqueca", type = "Cachorro", race = "Buldogue", time = "26 dias", status = "Aprovado", imageAnimal = drawable.mini_dog),
        Animals(id = 7, name = "Chico", type = "Gato", race = "Bombaim", time = "30 dias", status = "Cancelado", imageAnimal = drawable.cat),
        Animals(id = 8, name = "Luna", type = "Gato", race = "Siamês", time = "33 dias", status = "Pendente", imageAnimal = drawable.white_cat),
        Animals(id = 9, name = "Sushi", type = "Cachorro", race = "Vira-lata", time = "36 dias", status = "Pendente", imageAnimal = drawable.dog),
        Animals(id = 10, name = "Sofhi", type = "Cachorro", race = "Dachshund", time = "47 dias", status = "Aprovado", imageAnimal = drawable.sausage_dog)
    )
}

fun getAllAnimalsBySearch(name: String): List<Animals>{
    return getAllAnimals().filter {
        it.name.startsWith(prefix = name, ignoreCase = true)
//        it.type.startsWith(prefix = type, ignoreCase = true)
//        it.race.startsWith(prefix = race, ignoreCase = true)
    }
}