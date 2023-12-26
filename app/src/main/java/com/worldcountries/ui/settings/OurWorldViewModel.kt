package com.worldcountries.ui.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.worldcountries.domain.repository.WorldCountriesRepository
import com.worldcountries.model.country_detail.data.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OurWorldViewModel @Inject constructor(
    private val repository: WorldCountriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OurWorldUiState())
    val uiState: StateFlow<OurWorldUiState> get() = _uiState.asStateFlow()

    fun getWorldData(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val worldData = repository.getCountryDetail(context, "World")
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        worldData = worldData?.data
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = listOf(e.message ?: "Something went wrong !!")
                    )
                }
            }
        }
    }

    fun consumedError() {
        _uiState.update { it.copy(errorMessage = listOf()) }
    }
}

data class OurWorldUiState(
    val isLoading: Boolean = false,
    val errorMessage: List<String> = listOf(),
    val worldData: Data? = null
)