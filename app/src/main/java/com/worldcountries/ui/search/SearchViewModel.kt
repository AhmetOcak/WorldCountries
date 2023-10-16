package com.worldcountries.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.worldcountries.R
import com.worldcountries.common.Response
import com.worldcountries.data.repository.WorldCountriesRepository
import com.worldcountries.model.country.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: WorldCountriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun getCountryByName(countryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true, resultList = listOf()) }
            when (val response = repository.getCountryByName(countryName)) {
                is Response.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            resultList = response.data,
                            isError = false,
                            isSearchResultEmpty = false
                        )
                    }
                }

                is Response.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isError = R.string.unknown != response.messageId,
                            errorMessageId = response.messageId,
                            resultList = listOf(),
                            isSearchResultEmpty = R.string.unknown == response.messageId
                        )
                    }
                }
            }
        }
    }
}

data class SearchUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessageId: Int? = null,
    val isSearchResultEmpty: Boolean = false,
    val resultList: List<Country> = listOf()
)