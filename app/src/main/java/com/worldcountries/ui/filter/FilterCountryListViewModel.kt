package com.worldcountries.ui.filter

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

enum class FilterType {
    NOTHING,
    REGIONS,
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
                    FilterType.REGIONS -> { regions }
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
                title = "Filter",
                filters = listOf()
            )
        }
    }
}

data class FilterCountryListUiState(
    val isFilterTypeSelected: Boolean = false,
    val selectedFilterType: FilterType = FilterType.NOTHING,
    val title: String = "Filter",
    val filters: List<String> = listOf()
)

private val regions = listOf(
    "Africa",
    "Americas",
    "Asia",
    "Europe",
    "Oceania"
)

private val carSide = listOf(
    "left",
    "right"
)

private val continent = listOf(
    "Asia",
    "Africa",
    "Europe",
    "North America",
    "South America",
    "Oceania",
    "Antarctica"
)