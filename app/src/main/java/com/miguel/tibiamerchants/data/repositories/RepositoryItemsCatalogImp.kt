package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import com.miguel.tibiamerchants.domain.models.ItemsModels

class RepositoryItemsCatalogImp(private val retrofit: ApiClient): RepositoryItemsCatalog {
    override suspend fun items(): ItemsModels? {
        return try {
            val response = retrofit.items()
            println("Status Code: "+response.body())
            val resp = when(response.code()){
                200-> response.body()
                else -> null
            }
            resp
        }catch (e: Exception){
            println("Error Retrofit items(): "+e.message)
            null
        }
    }

}