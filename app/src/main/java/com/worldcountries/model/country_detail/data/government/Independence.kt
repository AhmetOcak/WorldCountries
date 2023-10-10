package com.worldcountries.model.country_detail.data.government

import com.google.gson.annotations.SerializedName

data class Independence(
    @SerializedName("date") val date: String? = null,
    @SerializedName("note") val from: String? = null
)
