package com.worldcountries.model.country

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") val name: Name? = Name(),
    @SerializedName("independent") val independent: Boolean? = null,
    @SerializedName("currencies") val currencies: Currencies? = Currencies(),
    @SerializedName("capital") val capital: ArrayList<String> = arrayListOf(),
    @SerializedName("altSpellings") val altSpellings: ArrayList<String> = arrayListOf(),
    @SerializedName("region") val region: String? = null,
    @SerializedName("subregion") val subregion: String? = null,
    @SerializedName("latlng") val latlng: ArrayList<Double> = arrayListOf(),
    @SerializedName("landlocked") val landlocked: Boolean? = null,
    @SerializedName("flag") val flag: String? = null,
    @SerializedName("maps") val maps: Maps? = Maps(),
    @SerializedName("population") val population: Int? = null,
    @SerializedName("car") val car: Car? = Car(),
    @SerializedName("timezones") val timezones: ArrayList<String> = arrayListOf(),
    @SerializedName("continents") val continents: ArrayList<String> = arrayListOf(),
    @SerializedName("flags") val flags: Flags? = Flags(),
    @SerializedName("coatOfArms") val coatOfArms: CoatOfArms? = CoatOfArms(),
    @SerializedName("startOfWeek") val startOfWeek: String? = null,
    @SerializedName("postalCode") val postalCode: PostalCode? = PostalCode(),
    @SerializedName("borders") val borders: ArrayList<String> = arrayListOf(),
    @SerializedName("area") val area: Double? = null
)