package com.worldcountries.model.favorite_country

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_country")
data class FavoriteCountryEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "common_name")
    val commonName: String,

    @ColumnInfo(name = "official_name")
    val officialName: String,

    @ColumnInfo(name = "capital")
    val capital: String,

    @ColumnInfo(name = "region")
    val region: String,

    @ColumnInfo(name = "population")
    val population: String,

    @ColumnInfo(name = "timezone")
    val timezone: String,

    @ColumnInfo(name = "alt_spellings")
    val altSpellings: String,

    @ColumnInfo(name = "flag_description")
    val flagDescription: String,

    @ColumnInfo(name = "introduction")
    val introduction: String,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "map_ref")
    val mapRef: String,

    @ColumnInfo(name = "coastline")
    val coastline: String,

    @ColumnInfo(name = "climate")
    val climate: String,

    @ColumnInfo(name = "terrain")
    val terrain: String,

    @ColumnInfo(name = "pop_dist")
    val popDist: String,

    @ColumnInfo(name = "nationality")
    val nationality: String,

    @ColumnInfo(name = "flag_img_url")
    val flagImgUrl: String,

    @ColumnInfo(name = "coat_of_arms")
    val coatOfArms: String,

    @ColumnInfo(name = "ethnic_groups_chart")
    val ethnicGroupsChart: List<ChartDataEntity>,

    @ColumnInfo(name = "religion_chart")
    val religionChart: List<ChartDataEntity>,

    @ColumnInfo(name = "population_chart")
    val populationChart: List<ChartDataEntity>
)