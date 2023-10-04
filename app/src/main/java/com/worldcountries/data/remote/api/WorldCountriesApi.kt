package com.worldcountries.data.remote.api

import com.worldcountries.common.EndPoints
import com.worldcountries.model.country.Country
import retrofit2.http.GET

interface WorldCountriesApi {

    @GET(EndPoints.allCountries)
    suspend fun getAllCountries(): List<Country>
}