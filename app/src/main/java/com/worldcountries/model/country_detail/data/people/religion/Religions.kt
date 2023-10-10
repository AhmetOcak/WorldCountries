package com.worldcountries.model.country_detail.data.people.religion

import com.google.gson.annotations.SerializedName

data class Religions(
    @SerializedName("religion") val religion: ArrayList<Religion> = arrayListOf(),
    @SerializedName("date") val date: String? = null
)