package com.worldcountries.model.country_detail.data.people

import com.google.gson.annotations.SerializedName
import com.worldcountries.model.country_detail.data.people.age_structure.AgeStructure
import com.worldcountries.model.country_detail.data.people.language.Languages
import com.worldcountries.model.country_detail.data.people.religion.Religions

data class People(
    @SerializedName("languages") val languages: Languages? = Languages(),
    @SerializedName("religions") val religions: Religions? = Religions(),
    @SerializedName("age_structure") val ageStructure: AgeStructure? = AgeStructure(),
    @SerializedName("population_distribution") val populationDistribution: String? = null
)