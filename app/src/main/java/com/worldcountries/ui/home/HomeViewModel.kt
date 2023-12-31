package com.worldcountries.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.worldcountries.common.Response
import com.worldcountries.domain.repository.WorldCountriesRepository
import com.worldcountries.model.country.Country
import com.worldcountries.model.filter.Filter
import com.worldcountries.model.filter.FilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

enum class SortType {
    NOTHING,
    LOWEST_POP,
    HIGHEST_POP
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WorldCountriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getCountryList()
    }

    private fun getCountryList() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            when (val response = repository.getAllCountries()) {
                is Response.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            countryList = response.data,
                            errorMessageId = null
                        )
                    }
                }

                is Response.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            errorMessageId = response.messageId
                        )
                    }
                }
            }
        }
    }

    fun filterCountryList(filters: ArrayList<Filter>) {
        val filteredList = mutableListOf<Country>()
        val currentList = _uiState.value.countryList

        _uiState.update {
            it.copy(isListFiltered = true)
        }

        filters.forEach { filter ->
            when (filter.filterType) {
                FilterType.CAR_SIDE -> {
                    if (filteredList.isNotEmpty()) {
                        filteredList.removeIf {
                            it.car?.side != filter.text.lowercase(Locale.getDefault())
                        }
                    } else {
                        filteredList.addAll(
                            currentList.filter {
                                it.car?.side == filter.text.lowercase(Locale.getDefault())
                            }
                        )
                    }
                }

                FilterType.CONTINENT -> {
                    filteredList.addAll(
                        currentList.filter {
                            it.continents.any { continent ->
                                continent == filter.text
                            }
                        }
                    )
                }

                else -> {
                    val str = filter.text.split('-')

                    if (filteredList.isNotEmpty()) {
                        filteredList.removeIf {
                            (it.population ?: 0) < str.first().toInt()
                                    &&
                                    (it.population ?: 0) > str.last().toInt()
                        }
                    } else {
                        filteredList.addAll(
                            currentList.filter {
                                (it.population ?: 0) >= str.first().toInt()
                                        &&
                                        (it.population ?: 0) <= str.last().toInt()
                            }
                        )
                    }
                }
            }
        }

        _uiState.update {
            it.copy(
                filteredList = filteredList,
                isFilteredListEmpty = filteredList.isEmpty()
            )
        }
    }

    fun setSortType(sortType: SortType) {
        _uiState.update {
            it.copy(sortType = sortType)
        }
    }

    fun sortCountryList() {
        val isListFiltered = _uiState.value.filteredList.isNotEmpty()
        val currentList = if (isListFiltered) {
            _uiState.value.filteredList
        } else {
            _uiState.value.countryList
        }

        when (_uiState.value.sortType) {
            SortType.NOTHING -> {
                if (isListFiltered) {
                    _uiState.update {
                        it.copy(filteredList = currentList)
                    }
                } else {
                    _uiState.update {
                        it.copy(countryList = currentList)
                    }
                }
            }

            SortType.HIGHEST_POP -> {
                if (isListFiltered) {
                    _uiState.update { state ->
                        state.copy(filteredList = currentList.sortedByDescending { it.population })
                    }
                } else {
                    _uiState.update { state ->
                        state.copy(countryList = currentList.sortedByDescending { it.population })
                    }
                }
            }

            SortType.LOWEST_POP -> {
                if (isListFiltered) {
                    _uiState.update { state ->
                        state.copy(filteredList = currentList.sortedBy { it.population })
                    }
                } else {
                    _uiState.update { state ->
                        state.copy(countryList = currentList.sortedBy { it.population })
                    }
                }
            }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isFilteredListEmpty: Boolean = false,
    val isListFiltered: Boolean = false,
    val errorMessageId: Int? = null,
    val sortType: SortType = SortType.NOTHING,
    val countryList: List<Country> = listOf(),
    val filteredList: List<Country> = listOf()
)