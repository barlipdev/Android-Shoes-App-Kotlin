package com.barlipdev.fitrite.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(
    val imageUrl: String,
    val smallImageUrl: String,
    val thumbUrl: String
) : Parcelable