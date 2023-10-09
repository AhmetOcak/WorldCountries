package com.worldcountries.ui.country

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.worldcountries.databinding.FragmentCountryBinding
import dagger.hilt.android.AndroidEntryPoint

private val tabsArray = arrayOf(
    "Overview",
    "Detail",
    "Map"
)

@AndroidEntryPoint
class CountryFragment : Fragment() {

    private var _binding : FragmentCountryBinding? = null
    private val binding : FragmentCountryBinding get() = _binding!!

    private val args: CountryFragmentArgs by navArgs()

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
        }

        val adapter = CountryFragmentAdapter(this, args.countryData)
        binding.vpCountry.adapter = adapter

        TabLayoutMediator(binding.tlCountry, binding.vpCountry) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}