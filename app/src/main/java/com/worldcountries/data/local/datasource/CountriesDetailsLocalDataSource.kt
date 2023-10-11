package com.worldcountries.data.local.datasource

import android.content.Context
import com.worldcountries.model.country_detail.Details

interface CountriesDetailsLocalDataSource {

    suspend fun readCountryDetailJson(
        applicationContext: Context,
        countryName: String
    ): Details?
}