package com.worldcountries.ui.filter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.worldcountries.databinding.ItemFilterBinding
import com.worldcountries.model.filter.Filter

class FilterAdapter(
    private val onChecked: (Filter) -> Unit
) : ListAdapter<Filter, FilterViewHolder>(FilterDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder =
        FilterViewHolder(
            ItemFilterBinding.inflate(LayoutInflater.from(parent.context)),
            onChecked
        )

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
    private val binding: ItemFilterBinding,
    private val onChecked: (Filter) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(value: Filter) {
        binding.apply {
            this.checkboxText = value.text
            this.isChecked = value.isChecked

            cbFilter.setOnClickListener {
                if (it is CheckBox) {
                    value.isChecked = it.isChecked
                    onChecked(value)
                }
            }

            executePendingBindings()
        }
    }
}