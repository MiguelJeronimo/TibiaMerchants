package com.miguel.tibiamerchants.data.network.retrofit

import com.miguel.tibiamerchants.domain.models.PriceTcModel
import com.miguel.tibiamerchants.domain.models.TibiaTradeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiTibiaTradeClient {
    @GET("api/tibiaCoinPrices")
    suspend fun tcPrices(): Response<PriceTcModel>

    @GET("api/trade")
    suspend fun trade(
        @Query("sortType") sortType: Int,
        @Query("page") page: Int
    ): Response<TibiaTradeModel>

    suspend fun trade(
        @QueryMap params: Map<String, String>
    ): Response<TibiaTradeModel>
}