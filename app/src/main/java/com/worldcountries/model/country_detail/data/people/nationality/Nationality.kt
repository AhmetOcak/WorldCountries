package com.worldcountries.model.country_detail.data.people.nationality

import com.google.gson.annotations.SerializedName

data class Nationality(
    @SerializedName("adjective") val nationality: String? = null
)
