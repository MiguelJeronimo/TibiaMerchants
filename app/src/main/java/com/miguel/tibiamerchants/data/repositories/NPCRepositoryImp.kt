package com.miguel.tibiamerchants.data.repositories

import Jsoup.Scrapper
import com.miguel.tibiamerchants.data.network.tibia.Tibia
import model.Tibia.NPC

class NPCRepositoryImp(val scrapper: Scrapper): NPCRepository {
    val rashid = "https://tibia.fandom.com/wiki/Rashid"//FUNCIONA
    val yasir = "https://tibia.fandom.com/wiki/Yasir"//FUNCIONA
    //dijins blue
    val horoun = "https://tibia.fandom.com/wiki/Haroun"
    val nashBob = "https://tibia.fandom.com/wiki/Nah%27Bob"
    //roshamuul
    val asnarus = "https://tibia.fandom.com/wiki/Asnarus"
    //dijings green
    val alesar = "https://tibia.fandom.com/wiki/Alesar"
    val yalam = "https://tibia.fandom.com/wiki/Yaman"
    //farmine npc
    val esrik = "https://tibia.fandom.com/wiki/Esrik"
    val alexander = "https://tibia.fandom.com/wiki/Alexander"
    val tamoril = "https://tibia.fandom.com/wiki/Tamoril"
    val grizzlyAdams = "https://tibia.fandom.com/wiki/Grizzly_Adams"

    override suspend fun rashid(): NPC? {
        try {
            val url = rashid
            val npc = Tibia(scrapper.Soup(url)).rashid()
            return npc
        } catch (e:Exception){
            println("Error Rashid: ${e}")
            return null
        }
    }

    override suspend fun yasir(): NPC? {
        try {
            val url = yasir
            val npc = Tibia(scrapper.Soup(url)).yasir()
            return npc
        } catch (e:Exception){
            println("Error yasir: ${e}")
            return null
        }
    }

    override suspend fun horoun(): NPC? {
        try {
            val url = horoun
            val npc = Tibia(scrapper.Soup(url)).horoun()
            return npc
        } catch (e:Exception){
            println("Error horoun: ${e}")
            return null
        }
    }

    override suspend fun nashBob(): NPC? {
        try {
            val url = nashBob
            val npc = Tibia(scrapper.Soup(url)).nashBob()
            return npc
        } catch (e:Exception){
            println("Error nashBob: ${e}")
            return null
        }
    }

    override suspend fun asnarus(): NPC? {
        try {
            val url = asnarus
            val npc = Tibia(scrapper.Soup(url)).asnarus()
            return npc
        } catch (e:Exception){
            println("Error asnarus: ${e}")
            return null
        }
    }

    override suspend fun alesar(): NPC? {
        try {
            val url = alesar
            val npc = Tibia(scrapper.Soup(url)).alesar()
            return npc
        } catch (e:Exception){
            println("Error alesar: ${e}")
            return null
        }
    }

    override suspend fun yalam(): NPC? {
        try {
            val url = yalam
            val npc = Tibia(scrapper.Soup(url)).yalam()
            return npc
        } catch (e:Exception){
            println("Error yalam: ${e}")
            return null
        }
    }

    override suspend fun esrik(): NPC? {
        try {
            val url = esrik
            val npc = Tibia(scrapper.Soup(url)).esrik()
            return npc
        } catch (e:Exception){
            println("Error esrik: ${e}")
            return null
        }
    }

    override suspend fun alexander(): NPC? {
        try {
            val url = alexander
            val npc = Tibia(scrapper.Soup(url)).alexander()
            return npc
        } catch (e:Exception){
            println("Error alexander: ${e}")
            return null
        }
    }

    override suspend fun tamoril(): NPC? {
        try {
            val url = tamoril
            val npc = Tibia(scrapper.Soup(url)).tamoril()
            return npc
        } catch (e:Exception){
            println("Error tamoril: ${e}")
            return null
        }
    }

    override suspend fun grizzlyAdams(): NPC? {
        try {
            val url = grizzlyAdams
            val npc = Tibia(scrapper.Soup(url)).grizzlyAdams()
            return npc
        } catch (e:Exception){
            println("Error grizzlyAdams: ${e}")
            return null
        }
    }
}