package com.miguel.tibiamerchants.domain.usecases

import android.util.Log
import androidx.paging.PagingData
import com.miguel.tibiamerchants.data.repositories.RepositoryTibiaClient
import com.miguel.tibiamerchants.domain.models.PriceTcModel
import com.miguel.tibiamerchants.domain.models.Profile
import com.miguel.tibiamerchants.domain.models.TibiaTradeItemModel
import com.miguel.tibiamerchants.domain.models.TibiaTradeModel
import com.miguel.tibiamerchants.domain.models.Trade
import kotlinx.coroutines.flow.Flow

class UseCaseTibiaTrade(private val repository: RepositoryTibiaClient) {
    suspend fun getTcPrice(): Result<PriceTcModel?>{
        return try {
            val response = repository.getTcPrices()
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun getTradeById(id: Int): Result<TibiaTradeItemModel?>{
        return try {
            val response = repository.getTradeById(id)
            Log.d("UseCase", "getTradeById: $response")
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun getTrade(page: Int, sortType: Int): Result<TibiaTradeModel?>{
        return try {
            val response = repository.getTrade(page, sortType)
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun getTrade(params: Map<String, String>): Result<TibiaTradeModel?>{
        return try {
            val response = repository.getTrade(params)
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    fun getItems(): Flow<PagingData<Trade>> {
        return repository.getItemsType()
    }

    suspend fun profile(userName:String): Result<Profile?> {
        return try {
            val response = repository.getUserProfile(userName)
            Log.d("UseCase Profile", "profile: $response")
            Result.success(response)
        }catch (e: Exception){
            Log.d("UseCase Profile", "Error: $e")
            Result.failure(e)
        }
    }
}