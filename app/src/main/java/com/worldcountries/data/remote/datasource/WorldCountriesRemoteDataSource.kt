package com.worldcountries.data.remote.datasource

import com.worldcountries.common.Response
import com.worldcountries.model.Country

interface WorldCountriesRemoteDataSource {

    suspend fun getAllCountries(): Response<List<Country>>
}