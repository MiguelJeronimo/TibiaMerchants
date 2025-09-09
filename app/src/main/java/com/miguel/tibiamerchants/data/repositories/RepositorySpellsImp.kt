package com.miguel.tibiamerchants.data.repositories

import android.util.Log
import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells

class RepositorySpellsImp(private val retrofit: ApiClient): RepositorySpells {
    override suspend fun spellsList(): ResponseSpells? {
        val response = retrofit.spellsList().body()
        Log.d("spells", response.toString())
        return response
    }
}