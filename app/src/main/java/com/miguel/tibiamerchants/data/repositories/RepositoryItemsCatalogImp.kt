package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import com.miguel.tibiamerchants.domain.models.ItemsModels

class RepositoryItemsCatalogImp(private val retrofit: ApiClient): RepositoryItemsCatalog {
    override suspend fun items(): ItemsModels? {
        return retrofit.items().body()
    }
}