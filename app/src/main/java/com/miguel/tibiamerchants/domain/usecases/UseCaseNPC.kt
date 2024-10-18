package com.miguel.tibiamerchants.domain.usecases

import com.miguel.tibiamerchants.data.repositories.NPCRepository
import model.Tibia.NPC

class UseCaseNPC(private val repository: NPCRepository) {
    suspend fun rashid(): NPC? {
        return repository.rashid()
    }
    suspend fun yasir(): NPC? {
        return repository.yasir()
    }
    suspend fun horoun(): NPC? {
        return repository.horoun()
    }
    suspend fun nashBob(): NPC?{
        return repository.nashBob()
    }
    suspend fun asnarus(): NPC?{
        return repository.asnarus()
    }
    suspend fun alesar(): NPC?{
        return repository.alesar()
    }
    suspend fun yalam(): NPC?{
        return repository.yalam()
    }
    suspend fun esrik(): NPC?{
        return repository.esrik()
    }
    suspend fun alexander(): NPC?{
        return repository.alexander()
    }
    suspend fun tamoril(): NPC?{
        return repository.tamoril()
    }
    suspend fun grizzlyAdams(): NPC?{
        return repository.grizzlyAdams()
    }
}