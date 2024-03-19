package br.com.fiap.animalmatchatt.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.animalmatchatt.model.Animals

@Dao
interface AnimalDao {

    @Insert
    fun save(animal: Animals): Long

    @Update
    fun update(animal: Animals): Int

    @Delete
    fun delete(animal: Animals): Int

    @Query("SELECT * FROM tbl_animal WHERE id = :id")
    fun findAnimalById(id: Int): Animals

    @Query("SELECT * FROM tbl_animal ORDER BY name ASC")
    fun listAnimal(): List<Animals>

}