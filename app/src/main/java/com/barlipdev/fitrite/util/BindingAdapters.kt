package com.barlipdev.fitrite.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?){
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String){
    Log.d("URLCHECK",url)
    Glide.with(imageView.context).load(url).into(imageView)
}