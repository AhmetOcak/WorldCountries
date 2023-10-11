package com.worldcountries.ui.country.tab_fragments.detail

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.worldcountries.R
import com.worldcountries.common.getChartColors
import com.worldcountries.common.modifyText
import com.worldcountries.databinding.FragmentDetailBinding
import com.worldcountries.model.country_detail.data.Data
import com.worldcountries.model.country_detail.data.people.age_structure.AgeStructure
import com.worldcountries.model.country_detail.data.people.ethnic_groups.Ethnicity
import com.worldcountries.model.country_detail.data.people.religion.Religions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment(private val countryName: String?) : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ethnicityEntries = mutableListOf<PieEntry>()
        val religionEntries = mutableListOf<PieEntry>()
        val popAgeEntries = mutableListOf<PieEntry>()
        val popGenderEntries = mutableListOf<PieEntry>()

        countryName?.let {
            viewModel.getCountryDetails(it, requireContext())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.apply {
                        isLoading = uiState.isLoading
                        isEthnicityDataEmpty = ethnicityEntries.isEmpty()
                        isReligionDataEmpty = religionEntries.isEmpty()
                        isPopAgeDataEmpty = popAgeEntries.isEmpty()
                    }

                    if (uiState.countryData != null) {
                        setEthnicityChart(
                            uiState.countryData.people?.ethnicGroups?.ethnicity,
                            ethnicityEntries
                        )
                        setReligionChart(uiState.countryData.people?.religions, religionEntries)
                        setPopAgeChart(uiState.countryData.people?.ageStructure, popAgeEntries)
                        setPopGenderChart(uiState.countryData.people?.ageStructure, popGenderEntries)
                        setTextData(uiState.countryData)
                    }
                }
            }
        }
    }

    private fun setPopGenderChart(data: AgeStructure?, entries: MutableList<PieEntry>) {
        val male = (data?.zeroToFourTeen?.males ?: 0) + (data?.fifteenToTwentyFour?.males ?: 0) +
                (data?.twentyFiveToFiftyFour?.males ?: 0) + (data?.fiftyFiveToSixtyFour?.males ?: 0) +
                (data?.sixtyFiveAndOver?.males ?: 0)

        val female = (data?.zeroToFourTeen?.females ?: 0) + (data?.fifteenToTwentyFour?.females ?: 0) +
                (data?.twentyFiveToFiftyFour?.females ?: 0) + (data?.fiftyFiveToSixtyFour?.females ?: 0) +
                (data?.sixtyFiveAndOver?.females ?: 0)

        entries.add(PieEntry(male.toFloat(), "Male"))
        entries.add(PieEntry(female.toFloat(), "Female"))

        val dataSet = PieDataSet(entries, data?.date ?: "")
        dataSet.colors = getChartColors(requireContext())

        val pieData = PieData(dataSet)
        binding.apply {
            pcPopGenderChart.data = pieData
            pcPopGenderChart.invalidate()
            pcPopGenderChart.description.text = "Gender"
            pcPopGenderChart.description.typeface = Typeface.DEFAULT_BOLD
        }
    }

    private fun setPopAgeChart(data: AgeStructure?, entries: MutableList<PieEntry>) {
        entries.add(PieEntry(data?.zeroToFourTeen?.percent?.toFloat() ?: 0f, "Ages 0-14"))
        entries.add(PieEntry(data?.fifteenToTwentyFour?.percent?.toFloat() ?: 0f, "Ages 15-24"))
        entries.add(PieEntry(data?.twentyFiveToFiftyFour?.percent?.toFloat() ?: 0f, "Ages 25-54"))
        entries.add(PieEntry(data?.fiftyFiveToSixtyFour?.percent?.toFloat() ?: 0f, "Ages 55-64"))
        entries.add(PieEntry(data?.sixtyFiveAndOver?.percent?.toFloat() ?: 0f, "Ages 65+"))

        val dataSet = PieDataSet(entries, data?.date ?: "")
        dataSet.colors = getChartColors(requireContext())

        val pieData = PieData(dataSet)
        binding.apply {
            isPopAgeDataEmpty = false
            pcPopAgeChart.data = pieData
            pcPopAgeChart.invalidate()
            pcPopAgeChart.description.text = "Age Structure"
            pcPopAgeChart.description.typeface = Typeface.DEFAULT_BOLD
        }
    }

    private fun setReligionChart(data: Religions?, entries: MutableList<PieEntry>) {
        var otherReligionPercent = 0f
        data?.religion?.forEach {
            if ((it.percent ?: 0.0) >= 5.0 && it.name?.contains("other") == false) {
                entries.add(PieEntry(it.percent?.toFloat() ?: 0f, it.name))
            } else {
                otherReligionPercent += it.percent?.toFloat() ?: 0f
            }
        }
        if (entries.isNotEmpty()) {
            entries.add(PieEntry(otherReligionPercent, "other"))

            val dataSet = PieDataSet(entries, data?.date ?: "")
            dataSet.colors = getChartColors(requireContext())

            val pieData = PieData(dataSet)
            binding.apply {
                isReligionDataEmpty = false
                pcReligionChart.data = pieData
                pcReligionChart.invalidate()
                pcReligionChart.description.text = "Religions"
                pcReligionChart.description.typeface = Typeface.DEFAULT_BOLD
            }
        } else {
            binding.isReligionDataEmpty = true
        }
    }

    private fun setEthnicityChart(
        data: ArrayList<Ethnicity>?,
        entries: MutableList<PieEntry>
    ) {
        var otherEthnicityPercent = 0f
        data?.forEach {
            if ((it.percent?.toDouble() ?: 0.0) >= 5.0 && it.name?.contains("other") == false) {
                entries.add(PieEntry(it.percent?.toFloat() ?: 0f, it.name))
            } else {
                otherEthnicityPercent += it.percent?.toFloat() ?: 0f
            }
        }
        if (entries.isNotEmpty()) {
            entries.add(PieEntry(otherEthnicityPercent, "other"))

            val dataSet = PieDataSet(entries, "")
            dataSet.colors = getChartColors(requireContext())

            val pieData = PieData(dataSet)
            binding.apply {
                isEthnicityDataEmpty = false
                pcEthnicityChart.data = pieData
                pcEthnicityChart.invalidate()
                pcEthnicityChart.description.text = "Ethnic Groups"
                pcEthnicityChart.description.typeface = Typeface.DEFAULT_BOLD
            }
        } else {
            binding.isEthnicityDataEmpty = true
        }
    }

    private fun setTextData(data: Data) {
        binding.apply {
            tvIntroduction.text = modifyText(
                "",
                data.introduction?.background
            )

            tvDetailLoc.text = modifyText(
                getString(R.string.detail_location),
                data.geography?.location
            )

            tvDetailMapRef.text = modifyText(
                getString(R.string.detail_map_reference),
                data.geography?.mapReferences
            )

            tvDetailCoastline.text = modifyText(
                getString(R.string.detail_coastline),
                "${data.geography?.coastline?.value} ${data.geography?.coastline?.units}"
            )

            tvDetailClimate.text = modifyText(
                getString(R.string.detail_climate),
                data.geography?.climate
            )

            tvDetailTerrain.text = modifyText(
                getString(R.string.detail_terrain),
                data.geography?.terrain
            )

            tvDetailPopDist.text = modifyText(
                getString(R.string.detail_population_distribution),
                data.geography?.populationDistribution
            )

            tvDetailNationality.text = modifyText(
                getString(R.string.detail_nationality),
                data.people?.nationality?.nationality
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}