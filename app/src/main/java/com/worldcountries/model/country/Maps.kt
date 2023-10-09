package com.worldcountries.model.country

import com.google.gson.annotations.SerializedName

data class Maps(
    @SerializedName("googleMaps") val googleMaps: String? = null,
    @SerializedName("openStreetMaps") val openStreetMaps: String? = null
)
