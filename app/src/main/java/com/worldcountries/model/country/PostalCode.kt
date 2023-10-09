package com.worldcountries.model.country

import com.google.gson.annotations.SerializedName

data class PostalCode(
    @SerializedName("format") val format: String? = null,
    @SerializedName("regex") val regex: String? = null
)
