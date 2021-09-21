package com.habib.testingairlift.data.remote.models

import com.habib.testingairlift.data.local.CryptoCurrenciesItem

class BaseModel(val results: List<BaseModelItem>) {


}

fun BaseModel.asDatabaseModel(): List<CryptoCurrenciesItem> {
    return results.map {
        CryptoCurrenciesItem(
            name = it.name,
            symbol = it.symbol,
            price = it.price,
            date = it.price_date
        )
    }
}