package com.habib.testingairlift.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CryptoCurrenciesItem::class],
    version = 1
)
abstract class CryptoCurrenciesDatabase : RoomDatabase() {
    abstract fun cryptoCurrenciesDao() : CryptoCurrenciesDao
}