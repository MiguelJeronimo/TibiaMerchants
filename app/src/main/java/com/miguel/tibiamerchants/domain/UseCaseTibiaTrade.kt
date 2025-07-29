package com.miguel.tibiamerchants.domain

import com.miguel.tibiamerchants.data.repositories.RepositoryTibiaClient
import com.miguel.tibiamerchants.domain.models.PriceTcModel

class UseCaseTibiaTrade(private val repository: RepositoryTibiaClient) {
    suspend fun getTcPrice(): Result<PriceTcModel?>{
        return try {
            val response = repository.getTcPrices()
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}