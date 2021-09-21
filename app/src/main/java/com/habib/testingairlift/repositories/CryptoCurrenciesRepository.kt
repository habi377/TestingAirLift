package com.habib.testingairlift.repositories

import androidx.lifecycle.LiveData
import com.habib.testingairlift.data.local.CryptoCurrenciesItem
import com.habib.testingairlift.data.local.models.CryptoCurrenciesDomainModel
import com.habib.testingairlift.data.remote.models.BaseModel
import com.habib.testingairlift.data.remote.models.BaseModelItem
import com.habib.testingairlift.utils.Resource


interface CryptoCurrenciesRepository {

//    suspend fun refreshCryptoCurrencies(convert : String) : Resource<BaseModel>
    suspend fun refreshCryptoCurrencies(convert : String) : Resource<List<BaseModelItem>>

    fun getAllCryptoCurrencies() : LiveData<List<CryptoCurrenciesDomainModel>>
}