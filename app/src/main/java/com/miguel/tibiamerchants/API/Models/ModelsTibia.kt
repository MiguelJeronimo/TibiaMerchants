package model.Tibia

data class NPCModel(
    var nameNPC: String?,
    var imgNPC: String?,
)

data class Rashid(
    var nameNPC: String?,
    var description: String?,
    var nearestCity: String?,
    var gender: String?,
    var race: String?,
    var job: String?,
    var version: String?,
    var status: String?,
    var imgNPC: String?,
    var map: String?,
    var items: ArrayList<Item>?
)

data class Yasir(
    var nameNPC: String?,
    var description: String?,
    var nearestCity: String?,
    var gender: String?,
    var race: String?,
    var job: String?,
    var version: String?,
    var status: String?,
    var imgNPC: String?,
    var map: String?,
    var items: ArrayList<Item>?
)

data class NPC (
    var nameNPC: String?,
    var description: String?,
    var nearestCity: String?,
    var gender: String?,
    var race: String?,
    var job: String?,
    var version: String?,
    var status: String?,
    var imgNPC: String?,
    var map: String?,
    var buyingItems: ArrayList<Item>?,
    var sellingItems: ArrayList<Item>?,
    var sellingSpells: ArrayList<Spells>?
)

data class Spells(
    var img: String?,
    var name:String?,
    var vocation: String?,
    var level: String?,
    var price:String?
)

data class Item(
    var name:String?,
    var img: String?,
    var price:String?
)
