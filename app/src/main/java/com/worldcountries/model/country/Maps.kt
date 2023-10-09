package com.worldcountries.model.country

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Maps(
    @SerializedName("googleMaps") val googleMaps: String? = null,
    @SerializedName("openStreetMaps") val openStreetMaps: String? = null
) : Parcelable
