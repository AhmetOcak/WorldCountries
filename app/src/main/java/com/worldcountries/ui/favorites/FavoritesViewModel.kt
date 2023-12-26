package com.worldcountries.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.worldcountries.domain.repository.WorldCountriesRepository
import com.worldcountries.model.favorite_country.FavoriteCountryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: WorldCountriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> get() = _uiState.asStateFlow()

    init {
        getFavoriteCountries()
    }

    private fun getFavoriteCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val favorites = repository.getAllFavoriteCountries()
                favorites.collect { data ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isFavListEmpty = data.isEmpty(),
                            favoriteCountryList = data
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isError = true, isLoading = false)
                }
            }
        }
    }
}

data class FavoritesUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isFavListEmpty: Boolean = false,
    val favoriteCountryList: List<FavoriteCountryEntity> = listOf()
)