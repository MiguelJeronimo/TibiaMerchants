package com.miguel.tibiamerchants.data.network.retrofit

import com.miguel.tibiamerchants.data.network.retrofit.responses.ApiResponse
import com.miguel.tibiamerchants.data.network.retrofit.responses.PriceModel
import com.miguel.tibiamerchants.data.network.retrofit.responses.TibiaTradeItemProfileModel
import com.miguel.tibiamerchants.data.network.retrofit.responses.TibiaTradeUserProfileModel
import com.miguel.tibiamerchants.domain.models.TibiaTradeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiTibiaTradeClient {
    @GET("api/v1/tibiaCoin/price")
    suspend fun tcPrices(): Response<ApiResponse<List<PriceModel>>>

    @GET("api/v1/trade/items")
    suspend fun trade(
        @Query("sortType") sortType: Int,
        @Query("page") page: Int,
        @Query("productType") productType: String? = null
    ): Response<ApiResponse<TibiaTradeModel>>

    suspend fun trade(
        @QueryMap params: Map<String, String>
    ): Response<TibiaTradeModel>

    @GET("api/v1/trade/item-profiles")
    suspend fun tradeById(
        @Query("id") id: Int,
        @Query("itemId") itemId: Int? = null,
        @Query("itemTear") itemTier: Int? = null,
        @Query("currencyType") currencyType: Int? = null,
        @Query("type") type: Int? = null,
    ): Response<ApiResponse<TibiaTradeItemProfileModel>>


    @GET("api/v1/trade/user-profile")
    suspend fun userProfile(@Query("username") user: String): Response<ApiResponse<TibiaTradeUserProfileModel>>
}