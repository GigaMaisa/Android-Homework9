package com.example.homeworknine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknine.databinding.RecyclerItemBinding

class ItemRecyclerAdapter : ListAdapter<Item ,ItemRecyclerAdapter.ItemViewHolder>(
    object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
) {
    private var originalList: List<Item> = mutableListOf()

    fun setOriginalList(list: List<Item>) {
        originalList = list
        submitList(list)
    }

    inner class ItemViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData() = with(binding) {
            with(currentList[adapterPosition]) {
                tvCategory.text = categoryType.toString()
                tvTitle.text = title
                tvPrice.text = price.toString()
                ivImage.setImageResource(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData()
    }
}