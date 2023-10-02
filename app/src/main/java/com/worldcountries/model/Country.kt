package com.worldcountries.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") var name: Name? = Name(),
    @SerializedName("independent") var independent: Boolean? = null,
    @SerializedName("currencies") var currencies: Currencies? = Currencies(),
    @SerializedName("capital") var capital: ArrayList<String> = arrayListOf(),
    @SerializedName("altSpellings") var altSpellings: ArrayList<String> = arrayListOf(),
    @SerializedName("region") var region: String? = null,
    @SerializedName("subregion") var subregion: String? = null,
    @SerializedName("latlng") var latlng: ArrayList<Double> = arrayListOf(),
    @SerializedName("landlocked") var landlocked: Boolean? = null,
    @SerializedName("flag") var flag: String? = null,
    @SerializedName("maps") var maps: Maps? = Maps(),
    @SerializedName("population") var population: Int? = null,
    @SerializedName("car") var car: Car? = Car(),
    @SerializedName("timezones") var timezones: ArrayList<String> = arrayListOf(),
    @SerializedName("continents") var continents: ArrayList<String> = arrayListOf(),
    @SerializedName("flags") var flags: Flags? = Flags(),
    @SerializedName("coatOfArms") var coatOfArms: CoatOfArms? = CoatOfArms(),
    @SerializedName("startOfWeek") var startOfWeek: String? = null,
    @SerializedName("postalCode") var postalCode: PostalCode? = PostalCode()
)