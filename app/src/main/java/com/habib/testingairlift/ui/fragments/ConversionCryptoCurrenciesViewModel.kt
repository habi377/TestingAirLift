package com.habib.testingairlift.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habib.testingairlift.repositories.CryptoCurrenciesRepository
import com.habib.testingairlift.utils.Constants
import com.habib.testingairlift.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class ConversionCryptoCurrenciesViewModel @Inject constructor(
    private val repository: CryptoCurrenciesRepository
) : ViewModel() {

    val _convertedResult = MutableLiveData<String>()

    val convertedResult: LiveData<String>
        get() = _convertedResult

    private var _responce = MutableLiveData<String>()
    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */
    val eventMessage: LiveData<String>
        get() = _responce

    fun convert(
        fromCurrency : String,
        amountStr: String,
        toCurrency: String
    ) {
        val fromAmount = amountStr.toFloatOrNull()
        if(fromAmount == null) {
            _responce.value = "Not a valid amount"
            return
        }

        _responce.value = Resource.Loading<String>().message
        viewModelScope.launch {
            val result = repository.refreshCryptoCurrencies(toCurrency)
            _responce.value = result.message
            when(result.message){
                Constants.SUCCESS->{
                    val rate = result.data?.find {
                        it.symbol == fromCurrency
                    }
                    rate?.price?.let {
                        val convertedCurrency = round(fromAmount * it *100 )/100
                        _convertedResult.value = "$fromAmount $fromCurrency = $convertedCurrency $toCurrency"
                    }
                }
            }
        }

    }
}