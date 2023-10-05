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
                title = "Filter"
            )
        }
    }
}

data class FilterCountryListUiState(
    val isFilterTypeSelected: Boolean = false,
    val selectedFilterType: FilterType = FilterType.NOTHING,
    val title: String = "Filter",
    val filters: List<Filter> = listOf()
)

private val regions = listOf(
    Filter("Africa", false),
    Filter("Americas", false),
    Filter("Asia", false),
    Filter("Europe", false),
    Filter("Oceania", false)
)

private val carSide = listOf(
    Filter("left", false),
    Filter("right", false)
)

private val continent = listOf(
    Filter("Asia", false),
    Filter("Africa", false),
    Filter("Europe", false),
    Filter("North America", false),
    Filter("South America", false),
    Filter("Oceania", false),
    Filter("Antarctica", false)
)