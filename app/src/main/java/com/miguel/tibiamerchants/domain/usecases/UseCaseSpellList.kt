package com.miguel.tibiamerchants.domain.usecases

import android.util.Log
import com.miguel.tibiamerchants.data.repositories.RepositorySpells
import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells

class UseCaseSpellList(private val repository: RepositorySpells) {
    suspend fun spells(): Result<ResponseSpells?> {
        return try {
            val response = repository.spellsList()
            Result.success(response)
        } catch (e: Exception){
            println("Error: "+e)
            Log.e("UseCaseSpellList", e.message.toString())
            Result.failure(e)
        }
    }
}