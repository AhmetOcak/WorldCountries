package com.worldcountries.model.country

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common") val common: String? = null,
    @SerializedName("official") val official: String? = null,
    @SerializedName("nativeName") val nativeName: NativeName? = NativeName()
)

data class NativeName(
    var nativeName: Native? = Native()
)

data class Native(
    @SerializedName("official") val official: String? = null,
    @SerializedName("common") val common: String? = null
)
