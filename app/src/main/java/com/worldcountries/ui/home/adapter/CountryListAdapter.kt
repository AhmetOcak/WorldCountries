package com.worldcountries.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.worldcountries.databinding.ItemCountryBinding
import com.worldcountries.model.Country

class CountryListAdapter : ListAdapter<Country, CountryViewHolder>(CountryDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(ItemCountryBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item.name?.common ?: "", item.flags?.png ?: "")
    }

    class CountryDiffUtilCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.independent == newItem.independent && oldItem.landlocked == newItem.landlocked
                    && oldItem.car == newItem.car && oldItem.altSpellings == newItem.altSpellings
                    && oldItem.region == newItem.region && oldItem.continents == newItem.continents
                    && oldItem.subregion == newItem.subregion && oldItem.startOfWeek == newItem.startOfWeek
                    && oldItem.timezones == newItem.timezones
        }
    }
}

class CountryViewHolder(
    private val binding: ItemCountryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(countryName: String, flagImgUrl: String) {
        binding.apply {
            this.name = countryName
            this.flagImgUrl = flagImgUrl
            executePendingBindings()
        }
    }
}