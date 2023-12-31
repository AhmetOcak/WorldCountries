package com.worldcountries.data.remote.datasource

import com.worldcountries.common.Response
import com.worldcountries.common.apiCall
import com.worldcountries.data.remote.api.WorldCountriesApi
import com.worldcountries.model.country.Country
import javax.inject.Inject

class WorldCountriesRemoteDataSourceImp @Inject constructor(
    private val worldCountriesApi: WorldCountriesApi
) : WorldCountriesRemoteDataSource {

    override suspend fun getAllCountries(): Response<List<Country>> =
        apiCall { worldCountriesApi.getAllCountries() }

    override suspend fun getCountryByName(name: String): Response<List<Country>> =
        apiCall { worldCountriesApi.getCountryByName(name) }
}