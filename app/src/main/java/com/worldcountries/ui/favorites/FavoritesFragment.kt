package com.worldcountries.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.worldcountries.R
import com.worldcountries.databinding.FragmentFavoritesBinding
import com.worldcountries.design.MarginItemDecoration
import com.worldcountries.model.country.CoatOfArms
import com.worldcountries.model.country.Country
import com.worldcountries.model.country.Flags
import com.worldcountries.model.country.Maps
import com.worldcountries.model.country.Name
import com.worldcountries.model.favorite_country.FavoriteCountryEntity
import com.worldcountries.ui.adapter.CountryListAdapter
import com.worldcountries.ui.adapter.Screen
import com.worldcountries.utils.getGridSpan
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    val binding: FragmentFavoritesBinding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CountryListAdapter(
            currentScreen = Screen.FAVORITES,
            navController = findNavController()
        )
        binding.rvFavoritesCountries.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(context, getGridSpan(requireContext()))
            addItemDecoration(
                MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.two_level_margin))
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.apply {
                        isLoading = uiState.isLoading
                        isFavListEmpty = uiState.isFavListEmpty
                    }

                    if (uiState.favoriteCountryList.isNotEmpty()) {
                        adapter.submitList(uiState.favoriteCountryList.toCountryModel())
                    }
                }
            }
        }
    }

    private fun List<FavoriteCountryEntity>.toCountryModel(): List<Country> {
        val favorites = arrayListOf<Country>()

        forEach {
            favorites.add(
                Country(
                    name = Name(
                        common = it.commonName,
                        official = it.officialName
                    ),
                    capital = arrayListOf(it.capital),
                    altSpellings = arrayListOf(
                        it.altSpellings
                            .replace('[', ' ')
                            .replace(']', ' ')
                            .removePrefix(" ")
                    ),
                    region = it.region,
                    subregion = it.subRegion,
                    landlocked = it.landlocked,
                    maps = Maps(googleMaps = it.mapUrl),
                    population = it.population.toInt(),
                    timezones = arrayListOf(it.timezone),
                    flags = Flags(
                        png = it.flagImgUrl,
                        alt = it.flagDescription
                    ),
                    coatOfArms = CoatOfArms(
                        png = it.coatOfArms
                    )
                )
            )
        }

        return favorites
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}