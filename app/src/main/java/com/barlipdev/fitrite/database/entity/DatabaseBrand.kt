package com.barlipdev.fitrite.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barlipdev.fitrite.domain.Brand
import com.barlipdev.fitrite.model.Media

@Entity(tableName = "brand")
data class DatabaseBrand(
    @PrimaryKey
    val idBrand: String,
    @Embedded
    val media: Media,
    val name: String
)

fun List<DatabaseBrand>.asDomainModel(): List<Brand>{
    return map {
        Brand(
            idBrand = it.idBrand,
            name = it.name,
            media = it.media
        )
    }
}
