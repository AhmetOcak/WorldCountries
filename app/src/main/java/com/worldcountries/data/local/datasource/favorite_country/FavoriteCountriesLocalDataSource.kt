package com.worldcountries.data.local.datasource.favorite_country

import com.worldcountries.model.favorite_country.FavoriteCountryEntity

interface FavoriteCountriesLocalDataSource {

    suspend fun addFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity)

    suspend fun deleteFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity)

    suspend fun getAllFavoriteCountries(): List<FavoriteCountryEntity>

    suspend fun getFavoriteCountry(countryName: String): FavoriteCountryEntity?
}