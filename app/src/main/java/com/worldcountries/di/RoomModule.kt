package com.worldcountries.di

import android.content.Context
import androidx.room.Room
import com.worldcountries.data.local.room.dao.FavoriteCountriesDao
import com.worldcountries.data.local.room.db.FavoriteCountriesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideFavoriteCountriesDb(@ApplicationContext context: Context): FavoriteCountriesDataBase {
        return Room.databaseBuilder(
            context,
            FavoriteCountriesDataBase::class.java,
            "favorite_countries_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoriteCountriesDao(db: FavoriteCountriesDataBase): FavoriteCountriesDao {
        return db.favoriteCountryDao()
    }
}