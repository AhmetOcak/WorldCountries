package com.worldcountries.model.country_detail.data.people.ethnic_groups

import com.google.gson.annotations.SerializedName

data class Ethnicity(
    @SerializedName("name") val name: String? = null,
    @SerializedName("percent") val percent: String? = null
)
