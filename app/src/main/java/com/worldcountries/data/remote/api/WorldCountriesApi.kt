package com.worldcountries.data.remote.api

import com.worldcountries.common.EndPoints
import com.worldcountries.model.country.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface WorldCountriesApi {

    @GET(EndPoints.allCountries)
    suspend fun getAllCountries(): List<Country>

    @GET(EndPoints.countryByName)
    suspend fun getCountryByName(@Path("name") name: String): List<Country>
}