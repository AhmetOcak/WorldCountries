package com.worldcountries.ui.country

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.worldcountries.data.repository.WorldCountriesRepository
import com.worldcountries.model.country.Country
import com.worldcountries.model.favorite_country.FavoriteCountryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class DatabaseOp {
    NOTHING,
    ADD,
    DELETE
}

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: WorldCountriesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryUiState())
    val uiState: StateFlow<CountryUiState> = _uiState.asStateFlow()

    private var countryName: String? = null

    init {
        countryName = savedStateHandle.get<Country>("countryData")?.name?.common
        countryName?.let { isCountryFavorite(it) }
    }

    fun addOrRemoveFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val favCountry = countryName?.let { repository.getFavoriteCountry(it) }
                if (favCountry == null) {
                    repository.addFavoriteCountry(favoriteCountryEntity)
                    _uiState.update {
                        it.copy(
                            isOperationAddOrRemove = DatabaseOp.ADD,
                            isCountryFavorite = true
                        )
                    }
                } else {
                    repository.deleteFavoriteCountry(favCountry)
                    _uiState.update {
                        it.copy(
                            isOperationAddOrRemove = DatabaseOp.DELETE,
                            isCountryFavorite = false
                        )
                    }
                }
                _uiState.update { it.copy(isSucceed = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isError = true) }
            }
        }
    }

    private fun isCountryFavorite(countryCommonName: String) {
        viewModelScope.launch {
            try {
                val favCountry = repository.getFavoriteCountry(countryCommonName)
                if (favCountry == null) {
                    _uiState.update { it.copy(isCountryFavorite = false) }
                } else {
                    _uiState.update { it.copy(isCountryFavorite = true) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isError = true) }
            }
        }
    }

    fun consumeEvent() {
        _uiState.update {
            it.copy(
                isSucceed = false,
                isError = false,
                isOperationAddOrRemove = DatabaseOp.NOTHING
            )
        }
    }
}

data class CountryUiState(
    val isSucceed: Boolean = false,
    val isError: Boolean = false,
    val isCountryFavorite: Boolean = false,
    val isOperationAddOrRemove: DatabaseOp = DatabaseOp.NOTHING
)