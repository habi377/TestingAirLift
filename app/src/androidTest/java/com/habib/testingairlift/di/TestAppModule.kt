package com.habib.testingairlift.di

import android.content.Context
import androidx.room.Room
import com.habib.testingairlift.data.local.CryptoCurrenciesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Named("test_db")
    fun provideInMemory(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context,CryptoCurrenciesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}