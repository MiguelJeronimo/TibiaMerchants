package com.miguel.tibiamerchants.data.network.retrofit

import com.miguel.tibiamerchants.domain.models.HouseHoldModel
import com.miguel.tibiamerchants.domain.models.ItemsModels
import com.miguel.tibiamerchants.domain.models.ItemsModelsType
import com.miguel.tibiamerchants.domain.models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.domain.models.OtherItemsModel
import com.miguel.tibiamerchants.domain.models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.ToolsAndOtherEquipmentModel
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

    //weapons
    @POST("api/v1/items/type/")
    fun itemsTypeHouseHold(@Body postItemsType: PostItemsType): Call<HouseHoldModel>

    //Plants Animals Products Food Drink catalog
    @POST("api/v1/items/type/")
    fun itemsTypeOthers(@Body postItemsType: PostItemsType): Call<PlantsAnimalsProductsFoodDrink>

    //Plants Animals Products Food Drink catalog
    @POST("api/v1/items/type/")
    fun itemsTypeToolsAndOthers(@Body postItemsType: PostItemsType): Call<ToolsAndOtherEquipmentModel>

    //Other items catalog
    @POST("api/v1/items/type/")
    fun itemsTypeOtherItems(@Body postItemsType: PostItemsType): Call<OtherItemsModel>

}