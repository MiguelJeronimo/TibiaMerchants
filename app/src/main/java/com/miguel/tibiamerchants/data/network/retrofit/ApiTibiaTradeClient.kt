package com.miguel.tibiamerchants.data.network.retrofit

import com.miguel.tibiamerchants.domain.models.PriceTcModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiTibiaTradeClient {
    @GET("api/tibiaCoinPrices")
    suspend fun tcPrices(): Response<PriceTcModel>
}