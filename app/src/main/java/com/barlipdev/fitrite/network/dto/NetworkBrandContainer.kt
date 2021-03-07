package com.barlipdev.fitrite.network.dto

import com.barlipdev.fitrite.database.entity.DatabaseBrand
import com.barlipdev.fitrite.domain.Brand
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkBrandContainer(val brands: List<BrandDTO>)

@JsonClass(generateAdapter = true)
data class BrandDTO(
    @Json(name = "idBrand") val brandId: String,
    val name: String,
    val image: String
)

fun NetworkBrandContainer.asDomainModel(): List<Brand>{
    return brands.map {
        Brand(
            name = it.name,
            image = it.image
        )
    }
}

fun NetworkBrandContainer.asDatabaseModel(): Array<DatabaseBrand>{
    return brands.map{
        DatabaseBrand(
            name = it.name,
            image = it.image
        )
    }.toTypedArray()
}
