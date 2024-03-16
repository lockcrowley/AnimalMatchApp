package br.com.fiap.animalmatchatt.repository

import br.com.fiap.animalmatchatt.model.Animals

fun getAllAnimals(): List<Animals> {

    return listOf(
        Animals(id = 1, name = "Penelope", type = "Gato", race = "Siamês", time = "2 dias", status = "Aprovado"),
        Animals(id = 2, name = "Jorge", type = "Cachorro", race = "Pug", time = "6 dias", status = "Pendente"),
        Animals(id = 3, name = "Flash", type = "Cachorro", race = "Poodle", time = "8 dias", status = "Aprovado"),
        Animals(id = 4, name = "Thor", type = "Cachorro", race = "Labrador", time = "12 dias", status = "Cancelado"),
        Animals(id = 5, name = "Olivia", type = "Gato", race = "Persa", time = "12 dias", status = "Pendente"),
        Animals(id = 6, name = "Panqueca", type = "Cachorro", race = "Buldogue", time = "26 dias", status = "Aprovado"),
        Animals(id = 7, name = "Chico", type = "Gato", race = "Bombaim", time = "30 dias", status = "Cancelado"),
        Animals(id = 8, name = "Luna", type = "Gato", race = "Siamês", time = "33 dias", status = "Pendente"),
        Animals(id = 9, name = "Sushi", type = "Cachorro", race = "Vira-lata", time = "36 dias", status = "Pendente"),
        Animals(id = 10, name = "Floquinho", type = "Cachorro", race = "Vira-lata", time = "47 dias", status = "Aprovado")
    )
}

fun getAllAnimalsBySearch(name: String): List<Animals>{
    return getAllAnimals().filter {
        it.name.startsWith(prefix = name, ignoreCase = true)
//        it.type.startsWith(prefix = type, ignoreCase = true)
//        it.race.startsWith(prefix = race, ignoreCase = true)
    }
}