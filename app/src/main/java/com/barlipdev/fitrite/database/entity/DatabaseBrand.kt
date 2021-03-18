package com.barlipdev.fitrite.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barlipdev.fitrite.domain.Brand

@Entity(tableName = "brand")
data class DatabaseBrand(
    @PrimaryKey
    val idBrand: String,
    val image: String,
    val name: String
)

fun List<DatabaseBrand>.asDomainModel(): List<Brand>{
    return map {
        Brand(
            name = it.name,
            image = it.image
        )
    }
}
