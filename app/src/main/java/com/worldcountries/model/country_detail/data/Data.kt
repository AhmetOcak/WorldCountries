package com.worldcountries.model.country_detail.data

import com.google.gson.annotations.SerializedName
import com.worldcountries.model.country_detail.data.geo.Geography
import com.worldcountries.model.country_detail.data.government.Government
import com.worldcountries.model.country_detail.data.people.People

data class Data(
  @SerializedName("name") val name: String? = null,
  @SerializedName("introduction") val introduction: Introduction? = Introduction(),
  @SerializedName("geography") val geography: Geography? = Geography(),
  @SerializedName("people") val people: People? = People(),
  @SerializedName("government") val government: Government? = Government()
)