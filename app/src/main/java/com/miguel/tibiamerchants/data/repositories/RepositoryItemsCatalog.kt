package com.miguel.tibiamerchants.data.repositories
import com.miguel.tibiamerchants.domain.models.ItemsModels



interface RepositoryItemsCatalog {
    suspend fun items(): ItemsModels?
}