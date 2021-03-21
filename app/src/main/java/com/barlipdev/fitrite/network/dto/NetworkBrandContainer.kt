package com.barlipdev.fitrite.network.dto

import com.barlipdev.fitrite.database.entity.DatabaseBrand
import com.barlipdev.fitrite.domain.Brand
import com.barlipdev.fitrite.model.Media
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkBrandContainer(val brands: List<BrandDTO>)

@JsonClass(generateAdapter = true)
data class BrandDTO(
    @Json(name = "idBrand") val brandId: String,
    val name: String,
    val media: Media,
)

fun NetworkBrandContainer.asDomainModel(): List<Brand>{
    return brands.map {
        Brand(
            idBrand = it.brandId,
            name = it.name,
            media = it.media
        )
    }
}

fun NetworkBrandContainer.asDatabaseModel(): Array<DatabaseBrand>{
    return brands.map{
        DatabaseBrand(
            idBrand = it.brandId,
            name = it.name,
            media = it.media
        )
    }.toTypedArray()
}
