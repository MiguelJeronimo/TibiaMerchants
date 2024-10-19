package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells

class RepositorySpellsImp(private val retrofit: ApiClient): RepositorySpells {
    override suspend fun spellsList(): ResponseSpells? {
        return try {
            val response = retrofit.spellsList()
            val resp = when(response.code()){
                200 -> response.body()
                else -> null
            }
            resp
        } catch (e:Exception){
            println("Error Retrofit spellsList(): "+e.message)
            null
        }
    }
}