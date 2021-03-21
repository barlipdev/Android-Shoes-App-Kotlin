package com.barlipdev.fitrite.domain

import com.barlipdev.fitrite.model.Media

data class Shoe (
    val modelName: String,
    val factor: Float,
    val colorWay: String,
    val gender: String,
    val releaseDate: String,
    val title: String,
    val media: Media,
    val brand: Brand)

