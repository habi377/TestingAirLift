package com.habib.testingairlift.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.habib.testingairlift.data.local.models.CryptoCurrenciesDomainModel
import com.habib.testingairlift.data.remote.models.BaseModel
import com.habib.testingairlift.data.remote.models.BaseModelItem
import com.habib.testingairlift.utils.Resource

/**
 * Test Doubles
 * Popular Types of Test Doubles are Fakes and Mocks
 * A Fake Test Doubles a Fake version of a class
 * .. is ver well suited for Test Cases But not for Production
 *  This is required to pass for Testing VM instead of Actual Repository which have API calls
 *  This is simulates the behaviour of our Default Repo instead of doing Actual API calls
 */
class FakeCryptoCurrenciesRepository : CryptoCurrenciesRepository {

    private val cryptoCurrenciesItems = mutableListOf<CryptoCurrenciesDomainModel>()

    private val allCryptoCurrenciesItems = MutableLiveData<List<CryptoCurrenciesDomainModel>>(cryptoCurrenciesItems)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError = value
    }

//    override suspend fun refreshCryptoCurrencies(convert: String): Resource<BaseModel> {
    override suspend fun refreshCryptoCurrencies(convert: String): Resource<List<BaseModelItem>> {
        return if (shouldReturnNetworkError){
            Resource.Error("Network Error")
        }else{
            cryptoCurrenciesItems.add(CryptoCurrenciesDomainModel("Etherium","ETH","20.00","2020-08-22"))
            cryptoCurrenciesItems.add(CryptoCurrenciesDomainModel("Etherium","ETH","20.00","2020-08-22"))
            cryptoCurrenciesItems.add(CryptoCurrenciesDomainModel("Etherium","ETH","20.00","2020-08-22"))
            allCryptoCurrenciesItems.value = cryptoCurrenciesItems
            Resource.Success(mutableListOf<BaseModelItem>())
        }
    }

    override fun getAllCryptoCurrencies(): LiveData<List<CryptoCurrenciesDomainModel>> {
        return allCryptoCurrenciesItems
    }
}