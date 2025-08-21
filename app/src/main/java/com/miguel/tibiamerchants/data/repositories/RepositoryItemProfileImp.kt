package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibia_merchants_api.domain.models.ResponseItemProfile
import com.miguel.tibiamerchants.data.network.retrofit.ApiClient

class RepositoryItemProfileImp(private val retrofit: ApiClient): RepositoryItemsProfile {

    override suspend fun item(name: String): ResponseItemProfile? {
        return retrofit.itemProfile(name).body()
    }
}