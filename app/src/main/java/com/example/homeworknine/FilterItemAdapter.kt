package com.example.homeworknine

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknine.databinding.FilterItemBinding

class FilterItemAdapter : ListAdapter<CategoryType, FilterItemAdapter.FilterItemViewHolder>(
    object : DiffUtil.ItemCallback<CategoryType>() {
        override fun areItemsTheSame(oldItem: CategoryType, newItem: CategoryType): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CategoryType, newItem: CategoryType): Boolean {
            return oldItem == newItem
        }
    }
) {
    private val categoriesList = listOf(
        "All",
        "\uD83C\uDF89\u00A0\u00A0 Party",
        "\uD83C\uDFD5️\u00A0\u00A0 Camping",
        "\uD83D\uDC54\u00A0\u00A0 Business",
        "\uD83C\uDFC5\u00A0\u00A0 Sports",
        "☃️\u00A0\u00A0 Winter"
    )

    var onFilterClick: ((CategoryType, AppCompatButton) -> Unit)? = null
    private lateinit var previousButton: AppCompatButton

    inner class FilterItemViewHolder(private val binding: FilterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData() {
            binding.btnFilter.text = categoriesList[adapterPosition]
        }

        fun clickFilter() {
            binding.btnFilter.setOnClickListener {
                changeButtonToUnselected()
                onFilterClick?.invoke(currentList[adapterPosition], binding.btnFilter)
            }
        }

        fun initializeAllButton() {
            if (adapterPosition == 0) {
                previousButton = binding.btnFilter
                previousButton.setBackgroundResource(R.drawable.background_selected_button)
                previousButton.setTextColor(Color.WHITE)
            }
        }

        private fun changeButtonToUnselected() {
            previousButton.setBackgroundResource(R.drawable.background_not_selected_button)
            previousButton.setTextColor(ContextCompat.getColor(binding.root.context, R.color.not_selected_button_text_color))
            previousButton = binding.btnFilter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterItemViewHolder {
        return FilterItemViewHolder(
            FilterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FilterItemViewHolder, position: Int) {
        holder.bindData()
        holder.clickFilter()
        holder.initializeAllButton()
    }

    fun setData(list: List<CategoryType>) {
        submitList(list)
    }
}