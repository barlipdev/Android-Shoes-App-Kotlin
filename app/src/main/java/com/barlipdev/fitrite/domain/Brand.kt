package com.barlipdev.fitrite.domain

import android.os.Parcelable
import com.barlipdev.fitrite.model.Media
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brand(
    val idBrand: String,
    val name: String,
    val media: Media
) : Parcelable