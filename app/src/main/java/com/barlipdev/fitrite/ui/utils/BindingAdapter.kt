package com.barlipdev.fitrite.ui.home

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barlipdev.fitrite.R
import com.barlipdev.fitrite.domain.Brand
import com.barlipdev.fitrite.domain.Collection
import com.barlipdev.fitrite.domain.Shoe
import com.barlipdev.fitrite.ui.home.brand.BrandAdapter
import com.barlipdev.fitrite.ui.home.shoe.ShoeAdapter
import com.bumptech.glide.Glide

@BindingAdapter("listData")
fun bindCollectionsRecyclerView(recyclerView: RecyclerView, data : List<Collection>?){
    val adapter = recyclerView.adapter as HomeCollectionAdapter
    adapter.submitList(data)
}

@BindingAdapter("brandList")
fun bindBrandRecyclerView(recyclerView: RecyclerView, data : List<Brand>?){
    val adapter = recyclerView.adapter as BrandAdapter
    adapter.submitList(data)
}

@BindingAdapter("shoeList")
fun bindShoeRecyclerView(recyclerView: RecyclerView, data: List<Shoe>?){
    val adapter= recyclerView.adapter as ShoeAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: FitriteApiStatus?) {
    when (status) {
        FitriteApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FitriteApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        FitriteApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?){
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String){
    Log.d("URLCHECK",url)
    Glide.with(imageView.context).load(url).into(imageView)
}
