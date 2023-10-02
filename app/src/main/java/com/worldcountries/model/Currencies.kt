package com.worldcountries.model

import com.google.gson.annotations.SerializedName

data class Currencies(
    var currency: Currency? = Currency()
)

data class Currency(
    @SerializedName("name") var name: String? = null,
    @SerializedName("symbol") var symbol: String? = null
)
