package com.worldcountries.ui.country.tab_fragments.detail

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.worldcountries.R
import com.worldcountries.databinding.FragmentDetailBinding
import com.worldcountries.model.country_detail.data.Data
import com.worldcountries.model.country_detail.data.people.age_structure.AgeStructure
import com.worldcountries.model.country_detail.data.people.ethnic_groups.Ethnicity
import com.worldcountries.model.country_detail.data.people.religion.Religions
import com.worldcountries.utils.ChartUtils.getChartColors
import com.worldcountries.utils.formatText
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
                        isCountryDataNull = uiState.countryData == null
                        isEthnicityDataEmpty = ethnicityEntries.isEmpty()
                        isReligionDataEmpty = religionEntries.isEmpty()
                        isPopAgeDataEmpty = popAgeEntries.isEmpty()
                    }

                    if (uiState.countryData != null && !uiState.isChartsDrew) {
                        setEthnicityChart(
                            uiState.countryData.people?.ethnicGroups?.ethnicity,
                            ethnicityEntries
                        )
                        setReligionChart(uiState.countryData.people?.religions, religionEntries)
                        setPopAgeChart(uiState.countryData.people?.ageStructure, popAgeEntries)
                        setPopGenderChart(uiState.countryData.people?.ageStructure, popGenderEntries)
                        setTextData(uiState.countryData)
                        viewModel.setCharsDrew()
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

        entries.add(PieEntry(male.toFloat(), getString(R.string.male)))
        entries.add(PieEntry(female.toFloat(), getString(R.string.female)))

        val dataSet = PieDataSet(entries, data?.date ?: "")
        dataSet.colors = getChartColors(requireContext())

        val pieData = PieData(dataSet)
        binding.apply {
            pcPopGenderChart.data = pieData
            pcPopGenderChart.description.text = getString(R.string.gender)
            pcPopGenderChart.description.typeface = Typeface.DEFAULT_BOLD
            pcPopGenderChart.isDrawHoleEnabled = false
            pcPopGenderChart.invalidate()
        }
    }

    private fun setPopAgeChart(data: AgeStructure?, entries: MutableList<PieEntry>) {
        entries.add(PieEntry(data?.zeroToFourTeen?.percent?.toFloat() ?: 0f, getString(R.string.age_one)))
        entries.add(PieEntry(data?.fifteenToTwentyFour?.percent?.toFloat() ?: 0f, getString(R.string.age_two)))
        entries.add(PieEntry(data?.twentyFiveToFiftyFour?.percent?.toFloat() ?: 0f, getString(R.string.age_three)))
        entries.add(PieEntry(data?.fiftyFiveToSixtyFour?.percent?.toFloat() ?: 0f, getString(R.string.age_four)))
        entries.add(PieEntry(data?.sixtyFiveAndOver?.percent?.toFloat() ?: 0f, getString(R.string.age_five)))

        val dataSet = PieDataSet(entries, data?.date ?: "")
        dataSet.colors = getChartColors(requireContext())

        val pieData = PieData(dataSet)
        binding.apply {
            isPopAgeDataEmpty = false
            pcPopAgeChart.data = pieData
            pcPopAgeChart.description.text = getString(R.string.age_struct)
            pcPopAgeChart.description.typeface = Typeface.DEFAULT_BOLD
            pcPopAgeChart.isDrawHoleEnabled = false
            pcPopAgeChart.invalidate()
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
            if (otherReligionPercent > 0f) {
                entries.add(PieEntry(otherReligionPercent, getString(R.string.other)))
            }

            val dataSet = PieDataSet(entries, data?.date ?: "")
            dataSet.colors = getChartColors(requireContext())

            val pieData = PieData(dataSet)
            binding.apply {
                isReligionDataEmpty = false
                pcReligionChart.data = pieData
                pcReligionChart.description.text = getString(R.string.religion)
                pcReligionChart.description.typeface = Typeface.DEFAULT_BOLD
                pcReligionChart.isDrawHoleEnabled = false
                pcReligionChart.invalidate()
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
            if (otherEthnicityPercent > 0f) {
                entries.add(PieEntry(otherEthnicityPercent, getString(R.string.other)))
            }

            val dataSet = PieDataSet(entries, "")
            dataSet.colors = getChartColors(requireContext())

            val pieData = PieData(dataSet)
            binding.apply {
                isEthnicityDataEmpty = false
                pcEthnicityChart.data = pieData
                pcEthnicityChart.description.text = getString(R.string.ethnic_groups)
                pcEthnicityChart.description.typeface = Typeface.DEFAULT_BOLD
                pcEthnicityChart.isDrawHoleEnabled = false
                pcEthnicityChart.invalidate()
            }
        } else {
            binding.isEthnicityDataEmpty = true
        }
    }

    private fun setTextData(data: Data) {
        binding.apply {
            tvIntroduction.text = formatText(
                "",
                data.introduction?.background
            )

            tvDetailLoc.text = formatText(
                getString(R.string.detail_location),
                data.geography?.location
            )

            tvDetailMapRef.text = formatText(
                getString(R.string.detail_map_reference),
                data.geography?.mapReferences
            )

            tvDetailCoastline.text = formatText(
                getString(R.string.detail_coastline),
                "${data.geography?.coastline?.value} ${data.geography?.coastline?.units}"
            )

            tvDetailClimate.text = formatText(
                getString(R.string.detail_climate),
                data.geography?.climate
            )

            tvDetailTerrain.text = formatText(
                getString(R.string.detail_terrain),
                data.geography?.terrain
            )

            tvDetailPopDist.text = formatText(
                getString(R.string.detail_population_distribution),
                data.geography?.populationDistribution ?: getString(R.string.no_pop_dist)
            )

            tvDetailNationality.text = formatText(
                getString(R.string.detail_nationality),
                data.people?.nationality?.nationality ?: getString(R.string.no_nation)
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}