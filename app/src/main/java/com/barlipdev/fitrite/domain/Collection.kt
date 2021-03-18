package com.barlipdev.fitrite.domain

data class Collection (
    val modelName: String,
    val factor: Double,
    val image: String,
    val brandName: String,
    val sizeUS: Double,
    val sizeUK: Double,
    val sizeEU: Double,
    val hiddenSizeUS: Double,
    val hiddenSizeUK: Double,
    val hiddenSizeEU: Double
        )

