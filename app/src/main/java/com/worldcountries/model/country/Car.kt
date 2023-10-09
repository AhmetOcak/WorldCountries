package com.worldcountries.model.country

import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("signs") val signs: ArrayList<String> = arrayListOf(),
    @SerializedName("side") val side: String? = null
)
