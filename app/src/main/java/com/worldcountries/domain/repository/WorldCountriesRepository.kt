package com.worldcountries.domain.repository

import android.content.Context
import com.worldcountries.common.Response
import com.worldcountries.model.country.Country
import com.worldcountries.model.country_detail.Details
import com.worldcountries.model.favorite_country.FavoriteCountryEntity
import kotlinx.coroutines.flow.Flow

interface WorldCountriesRepository {

    suspend fun getAllCountries(): Response<List<Country>>

    suspend fun getCountryByName(name: String): Response<List<Country>>

    suspend fun getCountryDetail(context: Context, countryName: String): Details?

    suspend fun addFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity)

    suspend fun deleteFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity)

    suspend fun getAllFavoriteCountries(): Flow<List<FavoriteCountryEntity>>

    suspend fun getFavoriteCountry(countryName: String): FavoriteCountryEntity?
}