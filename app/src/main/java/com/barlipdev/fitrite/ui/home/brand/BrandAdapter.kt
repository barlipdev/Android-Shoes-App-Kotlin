package com.barlipdev.fitrite.ui.home.brand

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barlipdev.fitrite.databinding.BrandItemBinding
import com.barlipdev.fitrite.domain.Brand

class BrandAdapter(val brandClickListener: BrandListener): ListAdapter<Brand,BrandAdapter.BrandViewHolder>(DiffCallback) {

    class BrandViewHolder(private val binding: BrandItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: BrandListener,brand: Brand){
            binding.brand = brand
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Brand>(){
        override fun areItemsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(BrandItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brand = getItem(position)
        holder.bind(brandClickListener,brand)
    }

    class BrandListener(val clickListener: (brand: Brand) -> Unit) {
        fun onClick(brand: Brand) = clickListener(brand)
    }
}

