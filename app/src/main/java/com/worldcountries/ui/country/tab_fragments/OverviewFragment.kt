package com.worldcountries.ui.country.tab_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.worldcountries.databinding.FragmentOverviewBinding
import com.worldcountries.model.country.Country
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment(private val countryData: Country) : Fragment() {

    private var _binding : FragmentOverviewBinding? = null
    private val binding : FragmentOverviewBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            commonName = countryData.name?.common
            officialName = countryData.name?.official
            capital = countryData.capital.first()
            altSpellings = countryData.altSpellings.toString()
                .replace('[', ' ')
                .replace(']', ' ')
            region = countryData.region
            subregion = countryData.subregion
            population = countryData.population.toString()
            landlocked = countryData.landlocked.toString()
            timezone = countryData.timezones.first()
            meaningOfFlag = countryData.flags?.alt
            flag = countryData.flags?.png
            coatOfArms = countryData.coatOfArms?.png
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}