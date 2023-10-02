package com.worldcountries.data.remote.datasource

import com.worldcountries.data.remote.api.WorldCountriesApi
import com.worldcountries.model.Country
import javax.inject.Inject

class WorldCountriesRemoteDataSourceImp @Inject constructor(
    private val worldCountriesApi: WorldCountriesApi
) : WorldCountriesRemoteDataSource {

    override suspend fun getAllCountries(): List<Country> = worldCountriesApi.getAllCountries()
}