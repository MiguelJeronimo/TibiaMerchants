package model.Tibia

import com.google.gson.annotations.SerializedName

data class NPCModel(
    @SerializedName("statusCode")
    val status: String,
    @SerializedName("body")
    val body: String,
)

data class NPC (
    @SerializedName("name")
    var nameNPC: String? = null,
    @SerializedName("city")
    val citys: Citys? = null,
    @SerializedName("description")
    var description: String?= null,
    @SerializedName("gender")
    var gender: String?= null,
    @SerializedName("race")
    var race: String?= null,
    @SerializedName("job")
    var job: String?= null,
    @SerializedName("version")
    var version: String?= null,
    @SerializedName("status")
    var status: String?= null,
    @SerializedName("imgNPC")
    var imgNPC: String?= null,
    @SerializedName("buyingItems")
    var buyingItems: ArrayList<Item>?= null,
    @SerializedName("sellingItems")
    var sellingItems: ArrayList<Item>?= null,
    @SerializedName("sellingSpells")
    var sellingSpells: ArrayList<Spells>?= null
)

data class Citys(
    @SerializedName("name")
    var name: String?,
    @SerializedName("map")
    var img: String?
)

data class Spells(
    var img: String?,
    var name:String?,
    var vocation: String?,
    var level: String?,
    var price:String?
)

data class Item(
    @SerializedName("name")
    var name:String?,
    @SerializedName("img")
    var img: String?,
    @SerializedName("price")
    var price:String?
)
