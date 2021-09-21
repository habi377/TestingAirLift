package com.habib.testingairlift.utils

import com.habib.testingairlift.data.local.models.CryptoCurrenciesDomainModel

class CurrencyClick(val block: (CryptoCurrenciesDomainModel) -> Unit) {
    /**
     * Called when a Currency Item is clicked
     *
     * @param currency the article that was clicked
     */
    fun onClick(currency: CryptoCurrenciesDomainModel) = block(currency)
}