package com.worldcountries.data.local.datasource.favorite_country

import com.worldcountries.model.favorite_country.FavoriteCountryEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteCountriesLocalDataSource {

    suspend fun addFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity)

    suspend fun deleteFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity)

    suspend fun getAllFavoriteCountries(): Flow<List<FavoriteCountryEntity>>

    suspend fun getFavoriteCountry(countryName: String): FavoriteCountryEntity?
}