package com.miguel.tibiamerchants.domain.usecases

import com.miguel.tibiamerchants.data.repositories.NPCRepository
import model.Tibia.NPCModel

class UseCaseNPC(private val repository: NPCRepository) {
    suspend fun npc(name: String): Result<NPCModel?> {
        return try {
            val response = repository.npc(name)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}