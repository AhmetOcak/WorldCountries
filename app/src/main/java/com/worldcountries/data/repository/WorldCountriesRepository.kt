package com.worldcountries.data.repository

import com.worldcountries.model.Country

interface WorldCountriesRepository {

    suspend fun getAllCountries(): List<Country>
}