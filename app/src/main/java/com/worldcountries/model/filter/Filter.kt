package com.worldcountries.model.filter

data class Filter(
    val id: Int,
    val text: String,
    var isChecked: Boolean,
    val filterType: FilterType
)

enum class FilterType {
    NOTHING,
    CAR_SIDE,
    CONTINENT,
    POPULATION
}
