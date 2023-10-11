package com.worldcountries.ui.country

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.worldcountries.model.country.Country
import com.worldcountries.ui.country.tab_fragments.detail.DetailFragment
import com.worldcountries.ui.country.tab_fragments.MapFragment
import com.worldcountries.ui.country.tab_fragments.OverviewFragment

private const val NUM_TABS = 3

class CountryFragmentAdapter(
    fragment: Fragment,
    private val countryData: Country
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OverviewFragment(countryData)
            1 -> DetailFragment(countryData.name?.common)
            else -> MapFragment(countryData.maps?.googleMaps)
        }
    }
}