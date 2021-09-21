package com.habib.testingairlift.di

import android.content.Context
import androidx.room.Room
import com.habib.testingairlift.data.local.CryptoCurrenciesDao
import com.habib.testingairlift.data.local.CryptoCurrenciesDatabase
import com.habib.testingairlift.data.remote.CryptoCurrenciesAPI
import com.habib.testingairlift.repositories.CryptoCurrenciesRepository
import com.habib.testingairlift.repositories.DefaultCryptoCurrenciesRepository
import com.habib.testingairlift.utils.Constants.BASE_URL
import com.habib.testingairlift.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCryptoCurrenciesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, CryptoCurrenciesDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDefaultCryptoCurrenciesRepository(
        dao: CryptoCurrenciesDao,
        api: CryptoCurrenciesAPI
    ) = DefaultCryptoCurrenciesRepository(dao,api) as CryptoCurrenciesRepository

    @Singleton
    @Provides
    fun provideCryptoCurrenciesDao(
        database: CryptoCurrenciesDatabase
    ) = database.cryptoCurrenciesDao()

    @Singleton
    @Provides
    fun provideCryptoCurrenciesApi(): CryptoCurrenciesAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CryptoCurrenciesAPI::class.java)
    }

}