package com.habib.testingairlift.data.local.models

import java.io.Serializable

data class CryptoCurrenciesDomainModel(
    // name , symbol , price , status
    var name: String,
    var symbol: String,
    var price: String,
    var date: String,
) : Serializable