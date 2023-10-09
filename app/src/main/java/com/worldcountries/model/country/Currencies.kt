package com.worldcountries.model.country

import com.google.gson.annotations.SerializedName

data class Currencies(
    var currency: Currency? = Currency()
)

data class Currency(
    @SerializedName("name") val name: String? = null,
    @SerializedName("symbol") val symbol: String? = null
)
