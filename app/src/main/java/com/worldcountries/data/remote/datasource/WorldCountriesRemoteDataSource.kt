package com.worldcountries.data.remote.datasource

import com.worldcountries.common.Response
import com.worldcountries.model.country.Country

interface WorldCountriesRemoteDataSource {

    suspend fun getAllCountries(): Response<List<Country>>

    suspend fun getCountryByName(name: String): Response<List<Country>>
}