package com.worldcountries.model.country

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoatOfArms(
    @SerializedName("png") val png: String? = null,
    @SerializedName("svg") val svg: String? = null
) : Parcelable