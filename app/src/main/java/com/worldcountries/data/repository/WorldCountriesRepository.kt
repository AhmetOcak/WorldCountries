package com.worldcountries.data.repository

import com.worldcountries.common.Response
import com.worldcountries.model.country.Country

interface WorldCountriesRepository {

    suspend fun getAllCountries(): Response<List<Country>>

    suspend fun getCountryByName(name: String): Response<List<Country>>
}