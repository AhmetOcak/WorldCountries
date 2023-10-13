package com.worldcountries.model.country

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    @SerializedName("name") val name: Name? = Name(),
    @SerializedName("capital") val capital: ArrayList<String> = arrayListOf(),
    @SerializedName("altSpellings") val altSpellings: ArrayList<String> = arrayListOf(),
    @SerializedName("region") val region: String? = null,
    @SerializedName("subregion") val subregion: String? = null,
    @SerializedName("landlocked") val landlocked: Boolean? = null,
    @SerializedName("maps") val maps: Maps? = Maps(),
    @SerializedName("population") val population: Int? = null,
    @SerializedName("car") val car: Car? = Car(),
    @SerializedName("timezones") val timezones: ArrayList<String> = arrayListOf(),
    @SerializedName("continents") val continents: ArrayList<String> = arrayListOf(),
    @SerializedName("flags") val flags: Flags? = Flags(),
    @SerializedName("coatOfArms") val coatOfArms: CoatOfArms? = CoatOfArms()
) : Parcelable