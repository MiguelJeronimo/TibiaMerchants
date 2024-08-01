package com.miguel.tibiamerchants.API.ApiTibia

import com.miguel.tibiamerchants.Models.ItemsModels
import com.miguel.tibiamerchants.Models.ItemsModelsType
import com.miguel.tibiamerchants.Models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.Models.PostItemsType
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @GET("api/v1/items")
    fun items(): Call<ItemsModels>

    @POST("api/v1/items/type/")
    fun itemsType(@Body postItemsType: PostItemsType): Call<ItemsModelsType>

    //weapons
    @POST("api/v1/items/type/")
    fun itemsTypeWeapons(@Body postItemsType: PostItemsType): Call<ItemsModelsTypeWeapons>
}