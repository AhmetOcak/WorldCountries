package com.worldcountries.ui.country

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.worldcountries.R
import com.worldcountries.databinding.FragmentCountryBinding
import com.worldcountries.model.favorite_country.FavoriteCountryEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private val tabsArray = arrayOf(
    "Overview",
    "Detail",
    "Map"
)

@AndroidEntryPoint
class CountryFragment : Fragment() {

    private var _binding: FragmentCountryBinding? = null
    private val binding: FragmentCountryBinding get() = _binding!!

    private val args: CountryFragmentArgs by navArgs()

    private val viewModel: CountryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            flag = args.countryData.flags?.png
            officialName = args.countryData.name?.official
            ivAddFavCountry.setOnClickListener {

                viewModel.addOrRemoveFavoriteCountry(mapCountryDataToEntity())
            }
        }

        val adapter = CountryFragmentAdapter(this, args.countryData)
        binding.vpCountry.adapter = adapter

        TabLayoutMediator(binding.tlCountry, binding.vpCountry) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->

                    if (uiState.isCountryFavorite) {
                        binding.ivAddFavCountry.setImageDrawable(
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_fav_filled,
                                null
                            )
                        )
                    } else {
                        binding.ivAddFavCountry.apply {
                            setImageDrawable(
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.baseline_favorite_border,
                                    null
                                )
                            )
                            setColorFilter(Color.RED)
                        }
                    }

                    if (uiState.isError) {
                        showToastMessage("Something went wrong !!")
                        viewModel.consumeEvent()
                    }

                    if (uiState.isSucceed) {
                        when (uiState.isOperationAddOrRemove) {
                            DatabaseOp.ADD -> {
                                showToastMessage("Country successfully added to favorites.")
                            }

                            DatabaseOp.DELETE -> {
                                showToastMessage("Country successfully removed from favorites.")
                            }

                            else -> {}
                        }
                        viewModel.consumeEvent()
                    }
                }
            }
        }
    }

    private fun mapCountryDataToEntity(): FavoriteCountryEntity {
        val data = args.countryData
        return FavoriteCountryEntity(
            commonName = data.name?.common ?: "",
            officialName = data.name?.official ?: "",
            capital = data.capital.first(),
            population = data.population.toString(),
            timezone = data.timezones.first(),
            altSpellings = data.altSpellings.toString(),
            flagDescription = data.flags?.alt ?: "",
            coatOfArms = data.coatOfArms?.png ?: "",
            flagImgUrl = data.flags?.png ?: "",
            landlocked = data.landlocked ?: false,
            region = data.region ?: "",
            subRegion = data.subregion ?: "",
            mapUrl = data.maps?.googleMaps ?: ""
        )
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}