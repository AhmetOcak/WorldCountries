package com.worldcountries.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.worldcountries.data.repository.WorldCountriesRepository
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
    private val repository: WorldCountriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryUiState())
    val uiState: StateFlow<CountryUiState> = _uiState.asStateFlow()

    fun addOrRemoveFavoriteCountry(
        countryCommonName: String,
        favoriteCountryEntity: FavoriteCountryEntity
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val favCountry = repository.getFavoriteCountry(countryCommonName)
                if (favCountry == null) {
                    repository.addFavoriteCountry(favoriteCountryEntity)
                    _uiState.update { it.copy(isOperationAddOrRemove = DatabaseOp.ADD) }
                } else {
                    repository.deleteFavoriteCountry(favCountry)
                    _uiState.update { it.copy(isOperationAddOrRemove = DatabaseOp.DELETE) }
                }
                _uiState.update { it.copy(isSucceed = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isSucceed = false) }
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
    val isOperationAddOrRemove: DatabaseOp = DatabaseOp.NOTHING
)