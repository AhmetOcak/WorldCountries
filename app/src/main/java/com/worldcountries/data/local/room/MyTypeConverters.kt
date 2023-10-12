package com.worldcountries.data.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.worldcountries.model.favorite_country.ChartDataEntity

class MyTypeConverters {

    @TypeConverter
    fun toList(json: String): List<ChartDataEntity> {
        val listType = object : TypeToken<List<ChartDataEntity>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun toJson(list: List<ChartDataEntity>): String {
        return Gson().toJson(list)
    }
}