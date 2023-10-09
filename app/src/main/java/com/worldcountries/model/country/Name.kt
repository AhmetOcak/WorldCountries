package com.worldcountries.model.country

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Name(
    @SerializedName("common") val common: String? = null,
    @SerializedName("official") val official: String? = null,
    @SerializedName("nativeName") val nativeName: NativeName? = NativeName()
) : Parcelable

@Parcelize
data class NativeName(
    var nativeName: Native? = Native()
) : Parcelable

@Parcelize
data class Native(
    @SerializedName("official") val official: String? = null,
    @SerializedName("common") val common: String? = null
) : Parcelable
