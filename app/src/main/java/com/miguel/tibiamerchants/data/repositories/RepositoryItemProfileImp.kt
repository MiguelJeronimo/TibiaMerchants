package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibia_merchants_api.domain.models.ResponseItemProfile
import com.miguel.tibiamerchants.data.network.retrofit.ApiClient

class RepositoryItemProfileImp(private val retrofit: ApiClient): RepositoryItemsProfile {

    override suspend fun item(name: String): ResponseItemProfile? {
        return try {
            val response = retrofit.itemProfile(name)
            val item = when(response.code()){
                200->response.body()
                else-> null
            }
            item
        } catch (e:Exception){
            return null
        }
    }
}