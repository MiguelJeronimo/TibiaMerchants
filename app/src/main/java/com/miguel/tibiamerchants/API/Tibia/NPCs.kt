package API.Tibia

import model.Tibia.*
import org.jsoup.nodes.Document

open class NPCs(val scrapper: Document){
    val npc = NPC(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null)
    val npcNotes = scrapper.getElementById("npc-notes")
    val nameNPC = scrapper.getElementsByClass("mw-page-title-main").text()
    val imgNPC = scrapper.getElementById("twbox-image")?.select("img")?.attr("src")
    val mapImage = scrapper.getElementsByClass("map_image").select("img").attr("src")
    private val aside = scrapper.getElementsByClass("portable-infobox pi-background pi-border-color pi-theme-twbox pi-layout-default")
    val data = aside.select("[class=\"pi-data-value pi-font\"]").eachText()
    val nearestCity  = aside.select("[class=\"wds-tabs__tab-label\"]").text()
    val item = Item(null, null, null)
    val spells = Spells(null, null, null, null, null)
    val arrayBuyingItems = ArrayList<Item>()
    val arraSellingItems = ArrayList<Item>()
    val arraySellingSpells = ArrayList<Spells>()
    /**
     * Location:
     * Ashta'daramai (Blue Djinn Fortress).
     ***/
    fun horoun(): NPC {
        return npcData()
    }
    /**
     * Location:
     * Ashta'daramai (Blue Djinn Fortress).
     * **/
    fun nashBob(): NPC {
        return npcData()
    }

    /**
     * Location:
     * Upper Roshamuul, under Depot.
     * **/
    fun asnarus(): NPC {
        return npcData()
    }

    /**
     * Location:
     * Mal'ouquah (Green Djinn Fortress).
     * **/
    fun alesar(): NPC {
        return npcData()
    }

    /**
     * Location:
     * Mal'ouquah (Green Djinn Fortress).
     * **/
    fun yalam(): NPC {
        return npcData()
    }
    /**
     * Location:
     * Farmine, north of the steamship.
     * **/
    fun esrik(): NPC {
        return npcData()
    }

    /**
     * Location
     * Edron, Ivory Towers (four floors above ground level in the west tower).
     * **/
    fun alexander(): NPC {
        return npcData()
    }

    fun grizzlyAdams(): NPC {
        return npcData()
    }

    /**
     * Location
     * Magician Quarter, far north-east.
     * **/
    fun tamoril(): NPC {
        return npcSpells()
    }

    private fun npcData(): NPC {
        val npcNotes = scrapper.getElementById("npc-notes")
        npc.map = mapImage
        npc.imgNPC = imgNPC
        npc.nameNPC = nameNPC
        npc.nearestCity = nearestCity
        npc.gender = data[1]
        npc.race = data[2]
        npc.job = data[3]
        npc.version = data[4]
        npc.status = data[5]
        npc.description = npcNotes?.text()
        npc.buyingItems = getBuyingItems()
        npc.sellingItems = getSellingItems()
        return npc
    }

    private fun npcSpells(): NPC {
        npc.map = mapImage
        npc.imgNPC = imgNPC
        npc.nameNPC = nameNPC
        npc.nearestCity = nearestCity
        npc.gender = data[1]
        npc.race = data[2]
        npc.job = data[3]
        npc.version = data[4]
        npc.status = data[5]
        npc.description = npcNotes?.text()
        npc.buyingItems = getBuyingItems()
        npc.sellingItems = getSellingItems()
        return npc
    }

    private fun getSellingSpells(): ArrayList<Spells> {
        val tableSells = scrapper.getElementById("npc-trade-sells")
        val trSells = tableSells?.select("tbody")?.select("tr")
        trSells?.forEach {
            val img = it.select("a").select("img")
            if (img.isNotEmpty()) {
                val price = it.select("[style=white-space:nowrap]").text()
                val vocation = it.select("td")[2].text()
                val level = it.select("td")[3].text()
                spells.vocation = vocation
                spells.level = level
                spells.price = price
                val nameItem = it.select("a").select("img").attr("alt")
                if (img.attr("data-src") == "") {
                    spells.name = nameItem
                    spells.img = img.attr("src")
                } else {
                    spells.name = nameItem
                    spells.img = img.attr("data-src")
                }
                arraySellingSpells.add(
                    Spells(
                        spells.img,
                        spells.name,
                        spells.vocation,
                        spells.level,
                        spells.price
                    )
                )
            }
        }
       return arraySellingSpells
    }

    fun getBuyingItems(): ArrayList<Item> {
        val tableBuys = scrapper.getElementById("npc-trade-buys")
        val trBuys = tableBuys?.select("tbody")?.select("tr")
        trBuys?.forEach {
            val img = it.select("a").select("img")
            if (img.isNotEmpty()) {
                val price = it.select("[style=white-space:nowrap]").text()
                item.price = price
                val nameItem = it.select("a").select("img").attr("alt")
                if (img.attr("data-src") == "") {
                    item.name = nameItem
                    item.img = img.attr("src")
                } else {
                    item.name = nameItem
                    item.img = img.attr("data-src")
                }
                arrayBuyingItems.add(
                    Item(
                        item.name,
                        item.img,
                        item.price
                    )
                )
            }
        }
        return arrayBuyingItems
    }

    private fun getSellingItems(): ArrayList<Item>? {
        val tableSells = scrapper.getElementById("npc-trade-sells")
        val trSells = tableSells?.select("tbody")?.select("tr")
        trSells?.forEach {
            val img = it.select("a").select("img")
            if (img.isNotEmpty()) {
                val price = it.select("[style=white-space:nowrap]").text()
                item.price = price
                val nameItem = it.select("a").select("img").attr("alt")
                if (img.attr("data-src") == "") {
                    item.name = nameItem
                    item.img = img.attr("src")
                } else {
                    item.name = nameItem
                    item.img = img.attr("data-src")
                }
                arraSellingItems.add(
                    Item(
                        item.name,
                        item.img,
                        item.price
                    )
                )
            }
        }
        return arraSellingItems
    }
}