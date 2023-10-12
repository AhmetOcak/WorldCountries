package com.worldcountries.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.worldcountries.data.local.room.dao.FavoriteCountriesDao
import com.worldcountries.model.favorite_country.FavoriteCountryEntity

@Database(entities = [FavoriteCountryEntity::class], version = 1)
abstract class FavoriteCountriesDataBase : RoomDatabase() {

    abstract fun favoriteCountryDao() : FavoriteCountriesDao
}