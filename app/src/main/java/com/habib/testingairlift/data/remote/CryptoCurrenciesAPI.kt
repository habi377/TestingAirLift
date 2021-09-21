package com.habib.testingairlift.data.remote

import com.habib.testingairlift.BuildConfig
import com.habib.testingairlift.data.local.CryptoCurrenciesItem
import com.habib.testingairlift.data.remote.models.BaseModel
import com.habib.testingairlift.data.remote.models.BaseModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCurrenciesAPI {

    @GET("/v1/currencies/ticker")
    suspend fun getCryptoCurrencies(
//        @Path(PARAM_PERIOD) period : Int,
        @Query("key") apiKey : String = BuildConfig.API_KEY,
        @Query("convert") convert : String = "USD",
        @Query("per-page") perPage : String = "100",
        @Query("page") page : String = "1",
        @Query("interval") interval : String = "1d"
    ): Response<List<BaseModelItem>>
}