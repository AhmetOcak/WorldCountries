package com.worldcountries.model.country_detail.data.people.ethnic_groups

import com.google.gson.annotations.SerializedName

data class EthnicityList(
    @SerializedName("ethnicity") val ethnicity: ArrayList<Ethnicity> = arrayListOf()
)
