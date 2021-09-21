package com.habib.testingairlift.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.habib.testingairlift.data.local.CryptoCurrenciesDao
import com.habib.testingairlift.data.local.asDomainModel
import com.habib.testingairlift.data.local.models.CryptoCurrenciesDomainModel
import com.habib.testingairlift.data.remote.CryptoCurrenciesAPI
import com.habib.testingairlift.data.remote.models.BaseModel
import com.habib.testingairlift.data.remote.models.BaseModelItem
import com.habib.testingairlift.data.remote.models.asDatabaseModel
import com.habib.testingairlift.utils.Resource
import java.lang.Exception
import javax.inject.Inject


class DefaultCryptoCurrenciesRepository @Inject constructor(
    private val cryptoCurrenciesDao: CryptoCurrenciesDao,
    private val cryptoCurrenciesAPI: CryptoCurrenciesAPI
) : CryptoCurrenciesRepository {

    override suspend fun refreshCryptoCurrencies(convert: String) : Resource<List<BaseModelItem>> {
        return try {
            val response = cryptoCurrenciesAPI.getCryptoCurrencies(convert = convert)
            if (response.isSuccessful) {
                response.body()?.let {
                    cryptoCurrenciesDao.insertAll(it.asDatabaseModel())
                    return@let Resource.Success(it)
                } ?: Resource.Error("Exception : ${response.message()}")
            } else {
                Resource.Error("Exception : ${response.message()}")
            }
        }catch (e: Exception){
            Resource.Error("Exception : ${e.message}")
        }
    }

    override fun getAllCryptoCurrencies(): LiveData<List<CryptoCurrenciesDomainModel>> {
        return Transformations.map(cryptoCurrenciesDao.getCryptoCurrencies()) {
            it.asDomainModel()
        }
    }
}