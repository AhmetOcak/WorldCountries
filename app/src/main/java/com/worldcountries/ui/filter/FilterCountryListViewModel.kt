package com.worldcountries.ui.filter

import androidx.lifecycle.ViewModel
import com.worldcountries.model.filter.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

enum class FilterType {
    NOTHING,
    CAR_SIDE,
    CONTINENT,
    POPULATION
}

@HiltViewModel
class FilterCountryListViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(FilterCountryListUiState())
    val uiState: StateFlow<FilterCountryListUiState> = _uiState.asStateFlow()

    fun setFilterTypeSelected() {
        _uiState.update { it.copy(isFilterTypeSelected = true) }
    }

    fun setSelectedFilterType(filterType: FilterType) {
        _uiState.update {
            it.copy(
                selectedFilterType = filterType,
                filters = when(filterType) {
                    FilterType.CONTINENT -> { continent }
                    FilterType.CAR_SIDE -> { carSide }
                    else -> listOf()
                }
            )
        }
    }

    fun setTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun resetFilterParams() {
        _uiState.update {
            it.copy(
                isFilterTypeSelected = false,
                selectedFilterType = FilterType.NOTHING,
                title = "Filter"
            )
        }
    }

    fun onClickFilter(filter: Filter) {
        val currentFilters = _uiState.value.selectedFilters.toMutableList()
        val selectedFilter = currentFilters.firstOrNull { it.id == filter.id }

        if (selectedFilter == null) {
            currentFilters.add(filter)
            _uiState.update {
                it.copy(selectedFilters = currentFilters)
            }
        } else {
            currentFilters.remove(selectedFilter)
            _uiState.update {
                it.copy(selectedFilters = currentFilters)
            }
        }
    }

    fun clearSelectedFilters() {
        _uiState.update {
            it.copy(selectedFilters = listOf())
        }
    }

    fun applyFilters() {
        val appliedFilters = _uiState.value.appliedFilters.toMutableList()
        val selectedFilters = _uiState.value.selectedFilters

        if (appliedFilters.isEmpty()) {
            appliedFilters.addAll(selectedFilters)
        } else {
            selectedFilters.forEach {
                if (appliedFilters.contains(it)) {
                    appliedFilters.remove(it)
                } else {
                    appliedFilters.add(it)
                }
            }
        }

        _uiState.update {
            it.copy(
                appliedFilters = appliedFilters
            )
        }
    }

    fun resetFilterData() {
        carSide.forEach {
            it.isChecked = false
        }
        continent.forEach {
            it.isChecked = false
        }
    }
}

data class FilterCountryListUiState(
    val isFilterTypeSelected: Boolean = false,
    val selectedFilterType: FilterType = FilterType.NOTHING,
    val title: String = "Filter",
    val filters: List<Filter> = listOf(),
    val selectedFilters: List<Filter> = listOf(),
    val appliedFilters: List<Filter> = listOf()
)

private val carSide = listOf(
    Filter(1, "left", false),
    Filter(2, "right", false)
)

private val continent = listOf(
    Filter(3, "Asia", false),
    Filter(4, "Africa", false),
    Filter(5, "Europe", false),
    Filter(6, "North America", false),
    Filter(7, "South America", false),
    Filter(8, "Oceania", false),
    Filter(9, "Antarctica", false)
)