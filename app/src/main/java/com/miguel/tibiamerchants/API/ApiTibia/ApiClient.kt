package com.miguel.tibiamerchants.API.ApiTibia

import com.miguel.tibiamerchants.Models.HouseHoldModel
import com.miguel.tibiamerchants.Models.ItemsModels
import com.miguel.tibiamerchants.Models.ItemsModelsType
import com.miguel.tibiamerchants.Models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.Models.OtherItemsModel
import com.miguel.tibiamerchants.Models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.Models.PostItemsType
import com.miguel.tibiamerchants.Models.ToolsAndOtherEquipmentModel
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