package com.worldcountries.data.local.datasource.favorite_country

import com.worldcountries.data.local.room.dao.FavoriteCountriesDao
import com.worldcountries.model.favorite_country.FavoriteCountryEntity
import javax.inject.Inject

class FavoriteCountriesLocalDataSourceImpl @Inject constructor(
    private val dao: FavoriteCountriesDao
) : FavoriteCountriesLocalDataSource {
    override suspend fun addFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity) =
        dao.addFavoriteCountry(favoriteCountryEntity)

    override suspend fun deleteFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity) =
        dao.deleteFavoriteCountry(favoriteCountryEntity)

    override suspend fun getAllFavoriteCountries(): List<FavoriteCountryEntity> =
        dao.getAllFavoriteCountries()

    override suspend fun getFavoriteCountry(countryName: String): FavoriteCountryEntity? =
        dao.getFavoriteCountry(countryName)
}