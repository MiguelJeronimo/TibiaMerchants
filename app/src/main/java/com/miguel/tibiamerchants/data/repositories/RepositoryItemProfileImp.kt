package com.miguel.tibiamerchants.data.repositories

import android.util.Log
import com.miguel.tibia_merchants_api.domain.models.ResponseItemProfile
import com.miguel.tibiamerchants.data.network.retrofit.ApiClient

class RepositoryItemProfileImp(private val retrofit: ApiClient): RepositoryItemsProfile {

    override suspend fun item(name: String): ResponseItemProfile? {
        val response = retrofit.itemProfile(name)
        Log.d("Item", "code=${response.code()} body=${response.body()} error=${response.errorBody()?.string()}")
        return response.body()
    }
}