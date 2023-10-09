package com.worldcountries.model.country

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currencies(
    var currency: Currency? = Currency()
) : Parcelable

@Parcelize
data class Currency(
    @SerializedName("name") val name: String? = null,
    @SerializedName("symbol") val symbol: String? = null
) : Parcelable
