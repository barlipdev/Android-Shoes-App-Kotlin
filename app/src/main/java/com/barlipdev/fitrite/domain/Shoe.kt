package com.barlipdev.fitrite.domain

import android.os.Parcelable
import com.barlipdev.fitrite.model.Media
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe (
    val modelName: String,
    val factor: Float,
    val colorWay: String,
    val gender: String,
    val releaseDate: String,
    val title: String,
    val media: Media,
    val brand: Brand) : Parcelable

