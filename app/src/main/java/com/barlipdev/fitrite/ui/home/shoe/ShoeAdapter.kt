package com.barlipdev.fitrite.ui.home.shoe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barlipdev.fitrite.databinding.ShoeItemBinding
import com.barlipdev.fitrite.domain.Shoe

class ShoeAdapter(): ListAdapter<Shoe,ShoeAdapter.ShoeViewHolder>(DiffCallback) {

    class ShoeViewHolder(private val binding: ShoeItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(shoe: Shoe){
            binding.shoe = shoe
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Shoe>(){
        override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        return ShoeViewHolder(ShoeItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        val shoe = getItem(position)
        holder.bind(shoe)
    }

}