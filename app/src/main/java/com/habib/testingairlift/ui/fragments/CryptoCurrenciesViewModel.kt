package com.habib.testingairlift.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habib.testingairlift.repositories.CryptoCurrenciesRepository
import com.habib.testingairlift.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoCurrenciesViewModel @Inject constructor(
    private val repository: CryptoCurrenciesRepository
) : ViewModel() {

    val cryptoCurrenciesList = repository.getAllCryptoCurrencies()

    private var _responce = MutableLiveData<String>()
    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */
    val eventMessage: LiveData<String>
        get() = _responce

    init {
        refreshDataFromRepository()
    }

    fun refreshDataFromRepository() {
        _responce.value = Resource.Loading<String>().message
        viewModelScope.launch {
            val result = repository.refreshCryptoCurrencies("USD")
            _responce.value = result.message
        }
    }
}