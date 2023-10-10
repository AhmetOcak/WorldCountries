package com.worldcountries.model.country_detail.data.geo

import com.google.gson.annotations.SerializedName

data class Coastline(
    @SerializedName("value") val value: Double? = null,
    @SerializedName("units") val units: String? = null,
    @SerializedName("note") val note: String? = null
)