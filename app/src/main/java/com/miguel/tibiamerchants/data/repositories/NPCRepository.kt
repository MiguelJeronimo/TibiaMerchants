package com.miguel.tibiamerchants.data.repositories

import model.Tibia.NPC
import model.Tibia.NPCModel

interface NPCRepository {
    suspend fun npc(name:String):NPCModel?
}