package com.worldcountries.ui.country.tab_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.worldcountries.R
import com.worldcountries.databinding.FragmentOverviewBinding
import com.worldcountries.model.country.Country
import com.worldcountries.utils.formatText
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
            meaningOfFlag = countryData.flags?.alt
            flag = countryData.flags?.png
            coatOfArms = countryData.coatOfArms?.png

            setTextData()
        }
    }

    private fun FragmentOverviewBinding.setTextData() {
        tvCommonName.text = formatText(
            getString(R.string.overview_common_name),
            countryData.name?.common
        )

        tvOfficialName.text = formatText(
            getString(R.string.overview_official_name),
            countryData.name?.official
        )

        tvCapital.text = formatText(
            getString(R.string.overview_capital),
            if (countryData.capital.isNotEmpty()) countryData.capital.first() else getString(R.string.no_cap)
        )

        tvRegion.text = formatText(
            getString(R.string.region),
            countryData.region
        )

        tvSubregion.text = formatText(
            getString(R.string.overview_subregion),
            countryData.subregion ?: getString(R.string.no_sub_reg)
        )

        tvPop.text = formatText(
            getString(R.string.overview_overview_pop),
            String.format("%,d", countryData.population)
        )

        tvLandlocked.text = formatText(
            getString(R.string.overview_landlocked),
            countryData.landlocked.toString()
        )

        tvTimezone.text = formatText(
            getString(R.string.overview_timezone),
            countryData.timezones.first()
        )

        tvAltSpellings.text = formatText(
            getString(R.string.overview_alt_spellings),
            countryData.altSpellings.toString()
                .replace('[', ' ')
                .replace(']', ' ')
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}