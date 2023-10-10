package com.worldcountries.model.country_detail

import com.google.gson.annotations.SerializedName
import com.worldcountries.model.country_detail.data.Data
import com.worldcountries.model.country_detail.metadata.Metadata

data class Details(
  @SerializedName("data") val data: Data? = Data(),
  @SerializedName("metadata") val metadata: Metadata? = Metadata()
)