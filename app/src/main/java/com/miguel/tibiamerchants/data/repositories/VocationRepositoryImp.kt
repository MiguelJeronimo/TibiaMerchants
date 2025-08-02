package com.miguel.tibiamerchants.data.repositories

import android.util.Log
import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import com.miguel.tibiamerchants.domain.models.vocations.Vocation
import com.miguel.tibiamerchants.domain.models.vocations.Vocations

class VocationRepositoryImp(private val retrofit: ApiClient): VocationRepository {
    override suspend fun vocations(): Vocations? {
        return try {
            val response = retrofit.vocations()
            val resp = when(response.code()){
                200 -> response.body()
                else -> null
            }
            Log.d("VocationRepositoryImp", "vocations: $resp")
            resp
        } catch (e: Exception){
            Log.d("VocationRepositoryImp", "Error: ${e.message}")
            return null
        }
    }

    override suspend fun vocation(vocations: String): Vocation? {
        try {
            val response = retrofit.vocation(vocations)
            val resp = when(response.code()){
                200 -> response.body()
                else -> null
            }
            Log.d("VocationRepositoryImp", "vocation: $resp")
            return resp
        } catch (e: Exception){
            Log.d("VocationRepositoryImp", "Error: ${e.message}")
            return null
        }
    }
}