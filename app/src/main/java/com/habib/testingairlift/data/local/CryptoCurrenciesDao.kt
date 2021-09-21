package com.habib.testingairlift.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CryptoCurrenciesDao {
    @Query("select * from cryptoCurrency_items")
    fun getCryptoCurrencies(): LiveData<List<CryptoCurrenciesItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( articlesItem : List<CryptoCurrenciesItem>)
}