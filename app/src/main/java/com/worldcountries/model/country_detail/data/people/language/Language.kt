package com.worldcountries.model.country_detail.data.people.language

import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("name") val name: String? = null,
    @SerializedName("percent") val percent: Double? = null
)