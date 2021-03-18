package com.barlipdev.fitrite.ui.home.addCollection

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barlipdev.fitrite.databinding.BrandItemBinding
import com.barlipdev.fitrite.domain.Brand

class BrandAdapter: ListAdapter<Brand,BrandAdapter.BrandViewHolder>(DiffCallback) {

    class BrandViewHolder(private val binding: BrandItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(brand: Brand){
            binding.brand = brand
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
        holder.bind(brand)
    }

}