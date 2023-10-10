package com.worldcountries.model.country_detail.data.geo

import com.google.gson.annotations.SerializedName

data class Geography(
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("map_references") val mapReferences: String? = null,
    @SerializedName("coastline") val coastline: Coastline? = Coastline(),
    @SerializedName("climate") val climate: String? = null,
    @SerializedName("terrain") val terrain: String? = null,
    @SerializedName("population_distribution") val populationDistribution: String? = null
)