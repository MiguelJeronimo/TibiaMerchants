package com.miguel.tibiamerchants.domain.usecases

import com.miguel.tibiamerchants.data.repositories.RepositoryItemsCatalog
import com.miguel.tibiamerchants.domain.models.ItemsModels

class UseCaseItemsCatalog(private val repository: RepositoryItemsCatalog) {
    suspend fun items(): ItemsModels? {
        return repository.items()
    }
}