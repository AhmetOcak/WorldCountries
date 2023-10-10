package com.worldcountries.model.country_detail.data.people.religion

import com.google.gson.annotations.SerializedName

data class Religion(
    @SerializedName("name") val name: String? = null,
    @SerializedName("percent") val percent: Double? = null
)