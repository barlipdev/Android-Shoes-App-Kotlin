package com.barlipdev.fitrite.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.barlipdev.fitrite.domain.Brand
import com.barlipdev.fitrite.domain.Shoe
import com.barlipdev.fitrite.model.Media
import com.google.gson.annotations.SerializedName

@Entity(tableName = "shoes")
data class DatabaseShoe(
    @PrimaryKey
    val idShoes: String,
    val modelName: String,
    val factor: Float,
    val colorWay: String,
    val gender: String,
    val releaseDate: String,
    val title: String,
    @Embedded(prefix = "shoe_media")
    val media: Media,
    val brandName: String,
    @Embedded(prefix = "brand_media")
    val brandMedia: Media,
    val idBrand: String
)

fun List<DatabaseShoe>.asDomainModel(): List<Shoe>{
    return map{
        Shoe(
            modelName = it.modelName,
            factor = it.factor,
            colorWay = it.colorWay,
            gender = it.gender,
            releaseDate = it.releaseDate,
            title = it.title,
            media = it.media,
            brand = Brand(it.idBrand,it.brandName,it.brandMedia)
        )
    }
}