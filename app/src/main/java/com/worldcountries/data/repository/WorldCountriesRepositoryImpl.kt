package com.worldcountries.data.repository

import com.worldcountries.common.Response
import com.worldcountries.data.remote.datasource.WorldCountriesRemoteDataSource
import com.worldcountries.model.country.Country
import javax.inject.Inject

class WorldCountriesRepositoryImpl @Inject constructor(
    private val worldCountriesRemoteDataSource: WorldCountriesRemoteDataSource
) : WorldCountriesRepository {

    override suspend fun getAllCountries(): Response<List<Country>> {
        return worldCountriesRemoteDataSource.getAllCountries()
    }

    override suspend fun getCountryByName(name: String): Response<List<Country>> {
        return worldCountriesRemoteDataSource.getCountryByName(name)
    }
}