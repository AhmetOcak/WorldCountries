package com.worldcountries.model.country

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostalCode(
    @SerializedName("format") val format: String? = null,
    @SerializedName("regex") val regex: String? = null
) : Parcelable