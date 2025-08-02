package com.miguel.tibiamerchants.domain.usecases

import com.miguel.tibiamerchants.data.repositories.RepositoryItemsCatalog
import com.miguel.tibiamerchants.domain.models.ItemsModels
import java.io.IOException

class UseCaseItemsCatalog(private val repository: RepositoryItemsCatalog) {
    suspend fun items():Result<ItemsModels?> {
        return try {
            Result.success(repository.items())
        }catch(e: IOException) {
            Result.failure(e)
        }
    }
}