package com.miguel.tibiamerchants.data.network.retrofit

import com.miguel.tibiamerchants.domain.models.HouseHoldModel
import com.miguel.tibiamerchants.domain.models.ItemsModels
import com.miguel.tibiamerchants.domain.models.ItemsModelsType
import com.miguel.tibiamerchants.domain.models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.domain.models.OtherItemsModel
import com.miguel.tibiamerchants.domain.models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.ToolsAndOtherEquipmentModel
import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @GET("api/v1/items")
    suspend fun items(): Response<ItemsModels>

    @POST("api/v1/items/type/")
    suspend fun itemsType(@Body postItemsType: PostItemsType): Response<ItemsModelsType>

    //weapons
    @POST("api/v1/items/type/")
    suspend fun itemsTypeWeapons(@Body postItemsType: PostItemsType): Response<ItemsModelsTypeWeapons>

    //weapons
    @POST("api/v1/items/type/")
    suspend fun itemsTypeHouseHold(@Body postItemsType: PostItemsType): Response<HouseHoldModel>

    //Plants Animals Products Food Drink catalog
    @POST("api/v1/items/type/")
    suspend fun itemsTypeOthers(@Body postItemsType: PostItemsType): Response<PlantsAnimalsProductsFoodDrink>

    //Plants Animals Products Food Drink catalog
    @POST("api/v1/items/type/")
    suspend fun itemsTypeToolsAndOthers(@Body postItemsType: PostItemsType): Response<ToolsAndOtherEquipmentModel>

    //Other items catalog
    @POST("api/v1/items/type/")
    suspend fun itemsTypeOtherItems(@Body postItemsType: PostItemsType): Response<OtherItemsModel>

    //Spells list
    @GET("/api/v1/spells")
    suspend fun spellsList(): Response<ResponseSpells>



}