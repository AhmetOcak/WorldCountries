package com.worldcountries.ui.filter

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.worldcountries.databinding.FragmentFilterCountryListBinding
import com.worldcountries.model.filter.FilterType
import com.worldcountries.ui.filter.adapter.FilterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilterCountryListFragment : Fragment() {

    private var _binding: FragmentFilterCountryListBinding? = null
    private val binding: FragmentFilterCountryListBinding get() = _binding!!

    private val viewModel: FilterCountryListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterCountryListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FilterAdapter {
            viewModel.onClickFilter(it)
        }
        binding.rvFilter.apply {
            this.adapter = adapter
            itemAnimator = null
            stateListAnimator = null
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.btnFilter.setOnClickListener {
            when (viewModel.uiState.value.isFilterTypeSelected) {
                true -> {
                    viewModel.apply {
                        applyPopFilters(
                            binding.tfMinPop.text.toString(),
                            binding.tfMaxPop.text.toString()
                        )
                        applyFilters()
                        clearSelectedFilters()
                        resetFilterParams()
                    }
                }

                else -> {
                    viewModel.clearSelectedFilters()
                    viewModel.resetFilterData()
                    findNavController().apply {
                        previousBackStackEntry?.savedStateHandle?.set(
                            "filters",
                            viewModel.uiState.value.appliedFilters
                        )
                        popBackStack()
                    }
                }
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            if (viewModel.uiState.value.isFilterTypeSelected) {
                viewModel.resetFilterParams()
                adapter.submitList(listOf())
            } else {
                findNavController().popBackStack()
            }
        }

        setFilterButtonsListener()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.apply {
                        isFilterTypeSelected = uiState.isFilterTypeSelected
                        isPopFilterTypeSelected = uiState.isPopFilterSelected
                        title = uiState.title
                        isFilterButtonEnabled = if (!uiState.isFilterTypeSelected) {
                            uiState.appliedFilters.isNotEmpty()
                        } else {
                            true
                        }
                    }

                    if (uiState.filters.isNotEmpty()) {
                        adapter.submitList(uiState.filters)
                    } else {
                        adapter.submitList(listOf())
                    }
                }
            }
        }
    }

    private fun setFilterButtonsListener() {
        binding.btnContinent.setOnClickListener {
            viewModel.apply {
                setFilterTypeSelected()
                setSelectedFilterType(FilterType.CONTINENT)
                setTitle("Continent")
            }
        }

        binding.btnPop.setOnClickListener {
            viewModel.apply {
                setFilterTypeSelected()
                setSelectedFilterType(FilterType.POPULATION)
                setTitle("Population")
            }
        }

        binding.btnTrafficSide.setOnClickListener {
            viewModel.apply {
                setFilterTypeSelected()
                setSelectedFilterType(FilterType.CAR_SIDE)
                setTitle("Car Side")
            }
        }
    }
}