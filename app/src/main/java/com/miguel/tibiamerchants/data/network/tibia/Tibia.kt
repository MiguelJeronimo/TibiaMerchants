package com.miguel.tibiamerchants.data.network.tibia

import model.Tibia.NPC
import org.jsoup.nodes.Document

class Tibia(scrapper: Document): NPCs(scrapper) {
    //&path-prefix=en&format=original   agregar esto al final de la url de las imagenes

    fun rashid(): NPC {
        val rashid = NPC()
        val aside = scrapper.getElementsByClass("portable-infobox pi-background pi-border-color pi-theme-twbox pi-layout-default")
        val city = aside.select("[class=\"pi-data-value pi-font\"]").eachText()[0]
        rashid.map = mapImage
        rashid.description = scrapper.getElementById("npc-notes")?.text()
        rashid.imgNPC = imgNPC
        rashid.nameNPC = nameNPC
        rashid.nearestCity = city
        rashid.gender = data[7]
        rashid.race = data[8]
        rashid.job = data[9]
        rashid.version = data[10]
        rashid.status = data[11]
        rashid.buyingItems = getBuyingItems()
        return rashid
    }

    fun yasir(): NPC {
        val aside = scrapper.getElementsByClass("portable-infobox pi-background pi-border-color pi-theme-twbox pi-layout-default")
        val city = aside.select("[class=\"pi-data-value pi-font\"]").eachText()[0]
        val yasir = NPC()
        yasir.map = mapImage
        yasir.description = scrapper.getElementById("npc-notes")?.text()
        yasir.imgNPC = imgNPC
        yasir.nameNPC = nameNPC
        //Data [Carlin, Ankrahmun, Liberty Bay, Male, Human, Merchant, 9.40 December 14, 2011 Winter Update 2011, Active]
        yasir.nearestCity = city
        yasir.gender = data[3]
        yasir.race = data[4]
        yasir.job = data[5]
        yasir.version = data[6]
        yasir.status = data[7]
        yasir.buyingItems = getBuyingItems()
        return yasir
    }
}