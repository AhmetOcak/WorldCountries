package com.worldcountries.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.worldcountries.model.favorite_country.FavoriteCountryEntity

@Dao
interface FavoriteCountriesDao {

    @Insert
    fun addFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity)

    @Delete
    fun deleteFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity)

    @Query("SELECT * FROM favorite_country")
    suspend fun getAllFavoriteCountries(): List<FavoriteCountryEntity>
}