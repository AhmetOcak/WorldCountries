package com.worldcountries.model

import com.google.gson.annotations.SerializedName

data class CoatOfArms(
    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null
)
