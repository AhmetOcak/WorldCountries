package com.worldcountries.model.country_detail

import com.google.gson.annotations.SerializedName

data class CountriesDetails(
    @SerializedName("countries") val countries: ArrayList<Details> = arrayListOf()
)