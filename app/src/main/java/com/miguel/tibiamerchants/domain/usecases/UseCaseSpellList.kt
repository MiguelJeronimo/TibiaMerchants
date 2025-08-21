package com.miguel.tibiamerchants.domain.usecases

import com.miguel.tibiamerchants.data.repositories.RepositorySpells
import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells

class UseCaseSpellList(private val repository: RepositorySpells) {
    suspend fun spells(): Result<ResponseSpells?> {
        return try {
            val response = repository.spellsList()
            Result.success(response)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}