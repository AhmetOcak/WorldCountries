package com.worldcountries.ui.country.tab_fragments.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.worldcountries.data.repository.WorldCountriesRepository
import com.worldcountries.model.country_detail.data.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: WorldCountriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getCountryDetails(countryName: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val countryDetails = repository.getCountryDetail(context, countryName)
                _uiState.update {
                    it.copy(
                        countryData = countryDetails?.data,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, isError = true)
                }
            }
        }
    }
}

data class DetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val countryData: Data? = null
)