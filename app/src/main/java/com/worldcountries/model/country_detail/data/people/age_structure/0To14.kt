package com.worldcountries.model.country_detail.data.people.age_structure

import com.google.gson.annotations.SerializedName

data class ZeroToFourTeen(
    @SerializedName("percent") val percent: Double? = null,
    @SerializedName("males") val males: Int? = null,
    @SerializedName("females") val females: Int? = null
)