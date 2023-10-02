package com.worldcountries.data.remote.datasource

import com.worldcountries.model.Country

interface WorldCountriesRemoteDataSource {

    suspend fun getAllCountries(): List<Country>
}