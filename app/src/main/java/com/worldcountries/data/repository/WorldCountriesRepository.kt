package com.worldcountries.data.repository

import com.worldcountries.common.Response
import com.worldcountries.model.Country

interface WorldCountriesRepository {

    suspend fun getAllCountries(): Response<List<Country>>
}