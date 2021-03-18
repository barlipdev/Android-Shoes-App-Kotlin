package com.barlipdev.fitrite.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barlipdev.fitrite.databinding.CollectionItemBinding
import com.barlipdev.fitrite.domain.Collection

class HomeCollectionAdapter() : ListAdapter<Collection, HomeCollectionAdapter.CollectionPropertyViewHolder>(DiffCallback){

    class CollectionPropertyViewHolder(private val binding: CollectionItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(collection: Collection){
            binding.property = collection
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Collection>(){
        override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionPropertyViewHolder {
        return CollectionPropertyViewHolder(CollectionItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CollectionPropertyViewHolder, position: Int) {
        val collectionProperty = getItem(position)
        holder.bind(collectionProperty)
    }


}