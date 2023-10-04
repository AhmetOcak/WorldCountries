package com.worldcountries.model.country

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common") var common: String? = null,
    @SerializedName("official") var official: String? = null,
    @SerializedName("nativeName") var nativeName: NativeName? = NativeName()
)

data class NativeName(
    var nativeName: Native? = Native()
)

data class Native(
    @SerializedName("official") var official: String? = null,
    @SerializedName("common") var common: String? = null
)
