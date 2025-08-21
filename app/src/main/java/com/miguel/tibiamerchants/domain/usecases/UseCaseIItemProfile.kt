package com.miguel.tibiamerchants.domain.usecases

import com.miguel.tibia_merchants_api.domain.models.ResponseItemProfile
import com.miguel.tibiamerchants.data.repositories.RepositoryItemsProfile
import java.io.IOException

class UseCaseIItemProfile(private val repository: RepositoryItemsProfile) {
    suspend fun item(name:String): Result<ResponseItemProfile?> {
        return try {
            val response = repository.item(name)
            Result.success(response)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}