package com.worldcountries.di

import com.worldcountries.data.remote.datasource.WorldCountriesRemoteDataSource
import com.worldcountries.data.repository.WorldCountriesRepository
import com.worldcountries.data.repository.WorldCountriesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWorldCountriesRepository(
        remoteDataSource: WorldCountriesRemoteDataSource
    ): WorldCountriesRepository {
        return WorldCountriesRepositoryImpl(remoteDataSource)
    }
}