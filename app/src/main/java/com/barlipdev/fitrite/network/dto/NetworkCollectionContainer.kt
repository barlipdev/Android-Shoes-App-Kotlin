package com.barlipdev.fitrite.network.dto


import com.barlipdev.fitrite.database.entity.DatabaseCollection
import com.barlipdev.fitrite.domain.Collection
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkCollectionContainer(val collections: List<CollectionDTO>)

@JsonClass(generateAdapter = true)
data class CollectionDTO(
    val idShoesCollection: String,
    val uid: String,
    val modelName: String,
    val factor: Double,
    val image: String,
    val brandName: String,
    val sizeUS: Double,
    val sizeUK: Double,
    val sizeEU: Double,
    val hiddenSizeUS: Double,
    val hiddenSizeUK: Double,
    val hiddenSizeEU: Double,
    val idShoes: String,
    val idSize: String,
    val idHiddenSize: String
)

fun NetworkCollectionContainer.asDomainModel(): List<Collection>{
    return collections.map {
        Collection(
            modelName = it.modelName,
            factor = it.factor,
            image = it.image,
            brandName = it.brandName,
            sizeUS = it.sizeUS,
            sizeUK = it.sizeUK,
            sizeEU = it.sizeEU,
            hiddenSizeUS = it.hiddenSizeUS,
            hiddenSizeUK = it.hiddenSizeUK,
            hiddenSizeEU = it.hiddenSizeEU
        )
    }
}

fun NetworkCollectionContainer.asDatabaseModel(): Array<DatabaseCollection>{
    return collections.map {
        DatabaseCollection(
            idShoesCollection = it.idShoesCollection,
            uid = it.uid,
            modelName = it.modelName,
            factor = it.factor,
            image = it.image,
            brandName = it.brandName,
            sizeUS = it.sizeUS,
            sizeUK = it.sizeUK,
            sizeEU = it.sizeEU,
            hiddenSizeUS = it.sizeUS,
            hiddenSizeUK = it.hiddenSizeUK,
            hiddenSizeEU = it.hiddenSizeEU,
            idShoes = it.idShoes,
            idSize = it.idSize,
            idHiddenSize = it.idHiddenSize
        )
    }.toTypedArray()
}