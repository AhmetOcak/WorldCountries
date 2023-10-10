package com.worldcountries.model.country_detail.data.government

import com.google.gson.annotations.SerializedName

data class Government(
    @SerializedName("government_type") val type: String? = null,
    @SerializedName("independence") val independence: Independence = Independence()
)