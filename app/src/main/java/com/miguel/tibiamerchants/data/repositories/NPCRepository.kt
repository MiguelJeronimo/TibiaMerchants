package com.miguel.tibiamerchants.data.repositories

import model.Tibia.NPC

interface NPCRepository {
    suspend fun rashid(): NPC?
    suspend fun yasir(): NPC?
    suspend fun horoun(): NPC?
    suspend fun nashBob(): NPC?
    suspend fun asnarus(): NPC?
    suspend fun alesar(): NPC?
    suspend fun yalam(): NPC?
    suspend fun esrik(): NPC?
    suspend fun alexander(): NPC?
    suspend fun tamoril(): NPC?
    suspend fun grizzlyAdams(): NPC?
}