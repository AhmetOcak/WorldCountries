package com.worldcountries.di

import com.worldcountries.data.remote.api.WorldCountriesApi
import com.worldcountries.data.remote.datasource.WorldCountriesRemoteDataSource
import com.worldcountries.data.remote.datasource.WorldCountriesRemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideWorldCountriesDataSource(
        worldCountriesApi: WorldCountriesApi
    ): WorldCountriesRemoteDataSource {
        return WorldCountriesRemoteDataSourceImp(worldCountriesApi)
    }
}