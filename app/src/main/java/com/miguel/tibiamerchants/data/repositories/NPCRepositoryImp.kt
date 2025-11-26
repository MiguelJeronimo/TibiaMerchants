package com.miguel.tibiamerchants.data.repositories


import android.util.Log
import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import model.Tibia.NPCModel

class NPCRepositoryImp(val apiTibia: ApiClient): NPCRepository {
    override suspend fun npc(name: String): NPCModel? {
        val response = apiTibia.npc(name)
        Log.d("NPC", "code=${response.code()} body=${response.body()} error=${response.errorBody()?.string()}")
        return apiTibia.npc(name).body()
    }
}