package com.barlipdev.fitrite.network.dto

import com.barlipdev.fitrite.database.entity.DatabaseBrand
import com.barlipdev.fitrite.database.entity.DatabaseShoe
import com.barlipdev.fitrite.domain.Brand
import com.barlipdev.fitrite.domain.Shoe
import com.barlipdev.fitrite.model.Media
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkShoesContainer(val shoes: List<ShoeDTO>)

@JsonClass(generateAdapter = true)
data class ShoeDTO(
    val idShoes: String,
    val modelName: String,
    val factor: Float,
    val colorWay: String,
    val gender: String,
    val releaseDate: String,
    val title: String,
    val media: Media,
    val brandName: String,
    val brandMedia: Media,
    val idBrand: String
)

fun NetworkShoesContainer.asDomainModel(): List<Shoe>{
    return shoes.map {
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

fun NetworkShoesContainer.asDatabaseModel(): Array<DatabaseShoe>{
    return shoes.map{
        DatabaseShoe(
            idShoes = it.idShoes,
            modelName = it.modelName,
            factor = it.factor,
            colorWay = it.colorWay,
            gender = it.gender,
            releaseDate = it.releaseDate,
            title = it.title,
            media = it.media,
            brandName = it.brandName,
            brandMedia = it.brandMedia,
            idBrand = it.idBrand
        )
    }.toTypedArray()
}