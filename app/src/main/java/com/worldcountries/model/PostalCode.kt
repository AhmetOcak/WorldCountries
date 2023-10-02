package com.worldcountries.model

import com.google.gson.annotations.SerializedName

data class PostalCode(
    @SerializedName("format") var format: String? = null,
    @SerializedName("regex") var regex: String? = null
)
