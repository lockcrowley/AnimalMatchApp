package br.com.fiap.animalmatchatt.model
import br.com.fiap.animalmatchatt.R.*

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_animal")
data class Animals(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String = "",
    var type: String = "",
    var race: String = "",
    var time: String = "7 dias",
    var status: String = "Pendente",
    @ColumnInfo(name = "image_animal") var imageAnimal: Int = drawable.animaldefault
)

