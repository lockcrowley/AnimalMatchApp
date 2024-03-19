package br.com.fiap.animalmatchatt.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.animalmatchatt.model.Animals

@Database(
    entities = [Animals::class],
    version = 1
)

abstract class AnimalDb : RoomDatabase(){

    abstract fun animalDao(): AnimalDao

    companion object {

        private lateinit var instance: AnimalDb

        fun getDatabase(context: Context): AnimalDb {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        AnimalDb::class.java,
                        "animal_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}