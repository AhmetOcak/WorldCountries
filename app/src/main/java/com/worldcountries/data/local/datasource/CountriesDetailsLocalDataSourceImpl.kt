package com.worldcountries.data.local.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.worldcountries.model.country_detail.CountriesDetails
import com.worldcountries.model.country_detail.Details
import javax.inject.Inject

class CountriesDetailsLocalDataSourceImpl @Inject constructor() : CountriesDetailsLocalDataSource {

    override fun readCountryDetailJson(
        applicationContext: Context,
        countryName: String
    ): Details? {
        val jsonString: String = applicationContext.assets.open("country_detail.json")
            .bufferedReader()
            .use { it.readText() }

        val listObjects = object : TypeToken<CountriesDetails>() {}.type
        val detailList: CountriesDetails = Gson().fromJson(jsonString, listObjects)
        return detailList.countries.find { it.data?.name?.lowercase() == countryName.lowercase() }
    }
}