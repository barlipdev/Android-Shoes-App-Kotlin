package com.barlipdev.fitrite.database.entity

import androidx.room.Entity
import com.barlipdev.fitrite.domain.Brand

@Entity(tableName = "brand")
data class DatabaseBrand(
    val id: String,
    val name: String,
    val image: String
)

fun List<DatabaseBrand>.asDomainModel(): List<Brand>{
    return map {
        Brand(
            name = it.name,
            image = it.image
        )
    }
}