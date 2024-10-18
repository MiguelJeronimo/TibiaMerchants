package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.data.network.tibia.Tibia
import Jsoup.Scrapper
import model.Tibia.NPC

class Repository {
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

    fun rashid(): NPC? {
        try {
            val url = rashid
            val scrapper = Scrapper().Soup(url)
            val tibia = Tibia(scrapper).rashid()
            return tibia
        } catch (e:Exception){
            println("Error Rashid: ${e}")
            return null
        }
    }
    fun yasir(): NPC? {
        try {
            val url = yasir
            val scrapper = Scrapper().Soup(url)
            val tibia = Tibia(scrapper).yasir()
            return tibia
        } catch (e:Exception){
            println("Error yasir: ${e.message}")
            return null
        }
    }
    //dijins blue
    fun horoun(): NPC? {
        try {
            val url = horoun
            val scrapper = Scrapper().Soup(url)
            val tibia = Tibia(scrapper).horoun()
            return tibia
        } catch (e:Exception){
            println("Error horoun: ${e.message}")
            return null
        }
    }

    fun nashBob(): NPC? {
        try {
            val url = nashBob
            val scrapper = Scrapper().Soup(url)
            val tibia = Tibia(scrapper).nashBob()
            return tibia
        } catch (e:Exception){
            println("Error nashBob: ${e.message}")
            return null
        }
    }
    //roshamuul
    fun asnarus(): NPC? {
        try {
            val url = asnarus
            val scrapper = Scrapper().Soup(url)
            val tibia = Tibia(scrapper).asnarus()
            return tibia
        } catch (e:Exception){
            println("Error asnarus: ${e.message}")
            return null
        }
    }
    //green dijin
    fun alesar(): NPC? {
        try {
            val url = alesar
            val scrapper = Scrapper().Soup(url)
            val tibia = Tibia(scrapper).alesar()
            return tibia
        } catch (e:Exception){
            println("Error alesar: ${e.message}")
            return null
        }
    }

    fun yalam(): NPC? {
        try {
            val url = yalam
            val scrapper = Scrapper().Soup(url)
            val tibia = Tibia(scrapper).yalam()
            return tibia
        }catch (e:Exception){
            println("Error yalam: ${e.message}")
            return null
        }
    }
    //farmine npc
    fun esrik(): NPC? {
        try {
            val url = esrik
            val scrapper = Scrapper().Soup(url)
            val tibia = Tibia(scrapper).esrik()
            return tibia
        }catch (e:Exception){
            println("Error esrik: ${e.message}")
            return null
        }
    }

    fun alexander(): NPC? {
        try {
            val url = alexander
            val scrapper = Scrapper().Soup(url)
            val tibia = Tibia(scrapper).alexander()
            return tibia
        }catch (e:Exception){
            println("Error alexander: ${e.message}")
            return null
        }
    }
    fun tamoril(): NPC? {
        try {
            val url = tamoril
            val scrapper = Scrapper().Soup(url)
            val tibia = Tibia(scrapper).tamoril()
            return tibia
        }catch (e:Exception){
            println("Error tamoril: ${e.message}")
            return null
        }
    }
    fun grizzlyAdams(): NPC? {
        try {
            val url = grizzlyAdams
            val scrapper = Scrapper().Soup(url)
            val tibia = Tibia(scrapper).grizzlyAdams()
            return tibia
        }catch (e:Exception){
            println("Error grizzlyAdams: ${e.message}")
            return null
        }
    }

}