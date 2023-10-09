package com.worldcountries.model.country

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    @SerializedName("signs") val signs: ArrayList<String> = arrayListOf(),
    @SerializedName("side") val side: String? = null
) : Parcelable
