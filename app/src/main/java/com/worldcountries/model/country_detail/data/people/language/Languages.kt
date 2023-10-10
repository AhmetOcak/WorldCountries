package com.worldcountries.model.country_detail.data.people.language

import com.google.gson.annotations.SerializedName

data class Languages(
    @SerializedName("language") val language: ArrayList<Language> = arrayListOf(),
    @SerializedName("most_spoken_first_language") val mostSpokenFirstLanguage: String? = null,
    @SerializedName("note") val note: String? = null,
    @SerializedName("date") val date: String? = null
)