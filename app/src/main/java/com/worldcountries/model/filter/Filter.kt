package com.worldcountries.model.filter

data class Filter(
    val id: Int,
    var text: String,
    var isChecked: Boolean = false,
    val filterType: FilterType
)

enum class FilterType {
    NOTHING,
    CAR_SIDE,
    CONTINENT,
    POPULATION
}