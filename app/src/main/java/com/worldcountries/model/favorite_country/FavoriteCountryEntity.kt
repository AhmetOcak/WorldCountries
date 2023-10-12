package com.worldcountries.model.favorite_country

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_country")
data class FavoriteCountryEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "common_name")
    val commonName: String,

    @ColumnInfo(name = "official_name")
    val officialName: String,

    @ColumnInfo(name = "capital")
    val capital: String,

    @ColumnInfo(name = "population")
    val population: String,

    @ColumnInfo(name = "region")
    val region: String,

    @ColumnInfo(name = "sub_region")
    val subRegion: String,

    @ColumnInfo(name = "timezone")
    val timezone: String,

    @ColumnInfo(name = "alt_spellings")
    val altSpellings: String,

    @ColumnInfo(name = "landlocked")
    val landlocked: Boolean,

    @ColumnInfo(name = "flag_description")
    val flagDescription: String,

    @ColumnInfo(name = "map_url")
    val mapUrl: String,

    @ColumnInfo(name = "flag_img_url")
    val flagImgUrl: String,

    @ColumnInfo(name = "coat_of_arms")
    val coatOfArms: String
)