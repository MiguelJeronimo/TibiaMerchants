package com.miguel.tibiamerchants.data.repositories


import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import model.Tibia.NPCModel

class NPCRepositoryImp(val apiTibia: ApiClient): NPCRepository {
    override suspend fun npc(name: String): NPCModel? {
        return apiTibia.npc(name).body()
    }
}