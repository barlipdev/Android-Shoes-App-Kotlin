package com.barlipdev.fitrite.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barlipdev.fitrite.domain.Collection

@Entity(tableName = "collection")
data class DatabaseCollection(
    @PrimaryKey
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

fun List<DatabaseCollection>.asDomainModel(): List<Collection>{
    return map {
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