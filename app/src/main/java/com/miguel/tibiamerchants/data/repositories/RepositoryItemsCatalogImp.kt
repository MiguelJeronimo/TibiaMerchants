package com.miguel.tibiamerchants.data.repositories

import android.util.Log
import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import com.miguel.tibiamerchants.domain.models.ItemsModels

class RepositoryItemsCatalogImp(private val retrofit: ApiClient): RepositoryItemsCatalog {
    override suspend fun items(): ItemsModels? {
        val response = retrofit.items()
        Log.d("Items", "code=${response.code()} body=${response.body()} error=${response.errorBody()?.string()}")
        return response.body()
    }
}