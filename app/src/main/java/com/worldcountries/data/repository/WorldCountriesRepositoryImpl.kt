package com.worldcountries.data.repository

import android.content.Context
import com.worldcountries.common.Response
import com.worldcountries.data.local.datasource.country_detail.CountriesDetailsLocalDataSource
import com.worldcountries.data.remote.datasource.WorldCountriesRemoteDataSource
import com.worldcountries.model.country.Country
import com.worldcountries.model.country_detail.Details
import javax.inject.Inject

class WorldCountriesRepositoryImpl @Inject constructor(
    private val worldCountriesRemoteDataSource: WorldCountriesRemoteDataSource,
    private val countriesDetailsLocalDataSource: CountriesDetailsLocalDataSource
) : WorldCountriesRepository {

    override suspend fun getAllCountries(): Response<List<Country>> {
        return worldCountriesRemoteDataSource.getAllCountries()
    }

    override suspend fun getCountryByName(name: String): Response<List<Country>> {
        return worldCountriesRemoteDataSource.getCountryByName(name)
    }

    override suspend fun getCountryDetail(context: Context, countryName: String): Details? {
        return countriesDetailsLocalDataSource.readCountryDetailJson(context, countryName)
    }
}