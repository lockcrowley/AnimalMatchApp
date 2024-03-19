package br.com.fiap.animalmatchatt.database.repository

import android.content.Context
import br.com.fiap.animalmatchatt.database.dao.AnimalDb
import br.com.fiap.animalmatchatt.model.Animals

class AnimalRepository (context: Context) {

    private val db = AnimalDb.getDatabase(context).animalDao()

    fun save(animal: Animals): Long {
        return db.save(animal)
    }

    fun update(animal: Animals): Int {
        return db.update(animal)
    }

    fun delete(animal: Animals): Int {
        return db.delete(animal)
    }

    fun listAnimal(): List<Animals> {
        return db.listAnimal()
    }

    fun findAnimalById(id: Int): Animals {
        return db.findAnimalById(id)
    }
}