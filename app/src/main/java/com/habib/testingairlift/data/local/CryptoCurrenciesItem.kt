package com.habib.testingairlift.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.habib.testingairlift.data.local.models.CryptoCurrenciesDomainModel
import com.habib.testingairlift.utils.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class CryptoCurrenciesItem(
    // name , symbol , price , status
    var name: String,
    var symbol: String,
    var price: Double,
    var date: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)

fun List<CryptoCurrenciesItem>.asDomainModel(): List<CryptoCurrenciesDomainModel> {
    return map {
        CryptoCurrenciesDomainModel(
            name = it.name,
            symbol = it.symbol,
            price = it.price.toString(),
            date = it.date
        )
    }
}