package com.worldcountries.ui.filter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.worldcountries.databinding.ItemFilterBinding
import com.worldcountries.model.filter.Filter

class FilterAdapter : ListAdapter<Filter, FilterViewHolder>(FilterDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder =
        FilterViewHolder(ItemFilterBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class FilterDiffUtilCallback : DiffUtil.ItemCallback<Filter>() {
        override fun areItemsTheSame(oldItem: Filter, newItem: Filter): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: Filter, newItem: Filter): Boolean {
            return false
        }

    }
}

class FilterViewHolder(
    private val binding: ItemFilterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(value: Filter) {
        binding.apply {
            this.checkboxText = value.text
            this.isChecked = value.isChecked

            cbFilter.setOnCheckedChangeListener { _, isChecked ->
                value.isChecked = isChecked
            }

            executePendingBindings()
        }
    }
}