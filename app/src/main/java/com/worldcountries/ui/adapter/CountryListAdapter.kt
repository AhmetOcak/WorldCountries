package com.worldcountries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.worldcountries.databinding.ItemCountryBinding
import com.worldcountries.model.country.Country
import com.worldcountries.ui.favorites.FavoritesFragmentDirections
import com.worldcountries.ui.home.HomeFragmentDirections
import com.worldcountries.ui.search.SearchFragmentDirections

enum class Screen {
    HOME,
    SEARCH,
    FAVORITES
}

class CountryListAdapter(
    private val navController: NavController,
    private val currentScreen: Screen
) : ListAdapter<Country, CountryViewHolder>(CountryDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(ItemCountryBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(
            countryName = item.name?.common ?: "",
            flagImgUrl = item.flags?.png ?: "",
            onCountryDetailsClick = {
                navController.navigate(
                    when(currentScreen) {
                        Screen.HOME -> {
                            HomeFragmentDirections.actionHomeFragmentToCountryFragment(item)
                        }
                        Screen.FAVORITES -> {
                            FavoritesFragmentDirections.actionFavoritesFragmentToCountryFragment(item)
                        }
                        Screen.SEARCH -> {
                            SearchFragmentDirections.actionSearchFragmentToCountryFragment(item)
                        }
                    }
                )
            }
        )
    }

    class CountryDiffUtilCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.landlocked == newItem.landlocked && oldItem.car == newItem.car
                    && oldItem.altSpellings == newItem.altSpellings && oldItem.region == newItem.region
                    && oldItem.continents == newItem.continents && oldItem.subregion == newItem.subregion
                    && oldItem.timezones == newItem.timezones
        }
    }
}

class CountryViewHolder(
    private val binding: ItemCountryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(countryName: String, flagImgUrl: String, onCountryDetailsClick: () -> Unit) {
        binding.apply {
            this.name = countryName
            this.flagImgUrl = flagImgUrl
            btnCountryDetails.setOnClickListener {
                onCountryDetailsClick()
            }
            executePendingBindings()
        }
    }
}