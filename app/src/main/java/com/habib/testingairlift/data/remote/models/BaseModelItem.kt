package com.habib.testingairlift.data.remote.models

import com.google.gson.annotations.SerializedName
import com.habib.testingairlift.data.local.CryptoCurrenciesItem

data class BaseModelItem(
    @SerializedName("id") val id : String,
    @SerializedName("currency") val currency : String,
    @SerializedName("symbol") val symbol : String,
    @SerializedName("name") val name : String,
    @SerializedName("logo_url") val logo_url : String,
    @SerializedName("status") val status : String,
    @SerializedName("price") val price : Double,
    @SerializedName("price_date") val price_date : String,
    @SerializedName("price_timestamp") val price_timestamp : String,
    @SerializedName("circulating_supply") val circulating_supply : String,
    @SerializedName("max_supply") val max_supply : String,
    @SerializedName("market_cap") val market_cap : String,
    @SerializedName("market_cap_dominance") val market_cap_dominance : Double,
    @SerializedName("num_exchanges") val num_exchanges : String,
    @SerializedName("num_pairs") val num_pairs : String,
    @SerializedName("num_pairs_unmapped") val num_pairs_unmapped : String,
    @SerializedName("first_candle") val first_candle : String,
    @SerializedName("first_trade") val first_trade : String,
    @SerializedName("first_order_book") val first_order_book : String,
    @SerializedName("rank") val rank : String,
    @SerializedName("rank_delta") val rank_delta : String,
    @SerializedName("high") val high : Double,
    @SerializedName("high_timestamp") val high_timestamp : String,
    @SerializedName("1d") val interval : Interval
)

fun List<BaseModelItem>.asDatabaseModel(): List<CryptoCurrenciesItem> {
    return map {
        CryptoCurrenciesItem(
            name = it.name,
            symbol = it.symbol,
            price = it.price,
            date = it.price_date.substring(0,it.price_date.indexOf("T"))
        )
    }
}