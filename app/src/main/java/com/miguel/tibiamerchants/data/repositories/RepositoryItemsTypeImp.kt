package com.miguel.tibiamerchants.data.repositories

import android.util.Log
import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import com.miguel.tibiamerchants.domain.models.HouseHoldModel
import com.miguel.tibiamerchants.domain.models.ItemsModelsType
import com.miguel.tibiamerchants.domain.models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.domain.models.OtherItemsModel
import com.miguel.tibiamerchants.domain.models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.ToolsAndOtherEquipmentModel

class RepositoryItemsTypeImp(private val retrofit: ApiClient): RepositoryItemsType {
    override suspend fun itemsType(body: PostItemsType): ItemsModelsType? {
        val response = retrofit.itemsType(body)
        Log.d("itemsType", "code=${response.code()} body=${response.body()} error=${response.errorBody()?.string()}")
        return response.body()
    }

    override suspend fun itemsTypeWeapons(body: PostItemsType): ItemsModelsTypeWeapons? {
        val response = retrofit.itemsTypeWeapons(body)
        Log.d("itemsType", "code=${response.code()} body=${response.body()} error=${response.errorBody()?.string()}")
        return response.body()
    }

    override suspend fun itemsTypeHouseHold(body: PostItemsType): HouseHoldModel? {
        val response = retrofit.itemsTypeHouseHold(body)
        Log.d("itemsType", "code=${response.code()} body=${response.body()} error=${response.errorBody()?.string()}")
        return response.body()
    }

    override suspend fun itemsTypeOthers(body: PostItemsType): PlantsAnimalsProductsFoodDrink? {
        val response = retrofit.itemsTypeOthers(body)
        Log.d("itemsType", "code=${response.code()} body=${response.body()} error=${response.errorBody()?.string()}")
        return response.body()
    }

    override suspend fun itemsTypeToolsAndOthers(body: PostItemsType): ToolsAndOtherEquipmentModel? {
        val response = retrofit.itemsTypeToolsAndOthers(body)
        Log.d("itemsType", "code=${response.code()} body=${response.body()} error=${response.errorBody()?.string()}")
        return response.body()
    }

    override suspend fun itemsTypeOtherItems(body: PostItemsType): OtherItemsModel? {
        val response = retrofit.itemsTypeOtherItems(body)
        Log.d("items", "code=${response.code()} body=${response.body()} error=${response.errorBody()?.string()}")
        return response.body()
    }
}