package com.worldcountries.model.country_detail.metadata

import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("date") val date: String? = null,
    @SerializedName("source") val source: String? = null,
    @SerializedName("nearby_dates") val nearbyDates: String? = null
)