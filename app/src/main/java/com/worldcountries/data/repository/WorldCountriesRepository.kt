package com.worldcountries.data.repository

import android.content.Context
import com.worldcountries.common.Response
import com.worldcountries.model.country.Country
import com.worldcountries.model.country_detail.Details

interface WorldCountriesRepository {

    suspend fun getAllCountries(): Response<List<Country>>

    suspend fun getCountryByName(name: String): Response<List<Country>>

    suspend fun getCountryDetail(context: Context, countryName: String): Details?
}