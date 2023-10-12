package com.worldcountries.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.worldcountries.model.favorite_country.FavoriteCountryEntity

@Dao
interface FavoriteCountriesDao {

    @Insert
    suspend fun addFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity)

    @Delete
    suspend fun deleteFavoriteCountry(favoriteCountryEntity: FavoriteCountryEntity)

    @Query("SELECT * FROM favorite_country")
    suspend fun getAllFavoriteCountries(): List<FavoriteCountryEntity>

    @Query("SELECT * FROM favorite_country WHERE common_name == :favoriteCountryName")
    suspend fun getFavoriteCountry(favoriteCountryName: String): FavoriteCountryEntity?
}