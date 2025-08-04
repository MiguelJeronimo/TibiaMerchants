package com.miguel.tibiamerchants.domain.usecases

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.miguel.tibiamerchants.data.repositories.RepositoryItemsType
import com.miguel.tibiamerchants.domain.models.HouseHoldModel
import com.miguel.tibiamerchants.domain.models.ItemsModelsType
import com.miguel.tibiamerchants.domain.models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.domain.models.OtherItemsModel
import com.miguel.tibiamerchants.domain.models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.ToolsAndOtherEquipmentModel
import java.io.IOException

class UseCaseItemsType(private val repository: RepositoryItemsType) {
    suspend fun itemsType(body: PostItemsType): Result<ItemsModelsType?> {
        return try {
            val response = repository.itemsType(body)
            Result.success(response)
        }catch (e: IOException){
            Result.failure(e)
        }
    }
    suspend fun itemsTypeWeapons( body: PostItemsType): Result<ItemsModelsTypeWeapons?>{
        return try {
            val response = repository.itemsTypeWeapons(body)
            Log.d("UseCaseItemsType", "itemsTypeWeapons: $response")
            Result.success(response)
        }catch (e: IOException){
            Result.failure(e)
        }
    }
    suspend fun itemsTypeHouseHold(body: PostItemsType): Result<HouseHoldModel?>{
        return try {
            val response = repository.itemsTypeHouseHold(body)
            Result.success(response)
        }catch (e: IOException){
            Result.failure(e)
        }
    }
    suspend fun itemsTypeOthers(body: PostItemsType): Result<PlantsAnimalsProductsFoodDrink?>{
        return try {
            val response = repository.itemsTypeOthers(body)
            Result.success(response)
        }catch (e: IOException){
            Result.failure(e)
        }
    }
    suspend fun itemsTypeToolsAndOthers(body: PostItemsType): Result<ToolsAndOtherEquipmentModel?>{
        return try {
            val response = repository.itemsTypeToolsAndOthers(body)
            Result.success(response)
        }catch (e: IOException){
            Result.failure(e)
        }
    }
    suspend fun itemsTypeOtherItems(body: PostItemsType): Result<OtherItemsModel?>{
        return try {
            val response = repository.itemsTypeOtherItems(body)
            Result.success(response)
        }catch (e: IOException){
            Result.failure(e)
        }
    }
}