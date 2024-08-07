package com.miguel.tibiamerchants.Models

// items type models
data class ItemsModelsType(
    val statusCode:Int? = null,
    val body: ArrayList<BodyItemstype>? = null
)

data class BodyItemstype(
    val name: String? = null,
    val img: String? = null,
    val arm: String? = null,
    val defense: String? = null,
    val vol: String? = null,
    val weight: String? = null,
    val attributes: String? = null,
    val resist: String? = null,
    val slots: String? = null,
    val classs: String? = null,
    val level: String? = null,
    val vocation: String? = null,
)

//MODEL POST ITEMS TYPE ENDPOINT
data class PostItemsType(
    var title: String? = null,
    var name: String? = null
)

//weapons model response
    data class ItemsModelsTypeWeapons(
    val statusCode: Int? = null,
    val body: BodyItemstypeWeapons? = null,
)

data class BodyItemstypeWeapons(
    var weapons: ArrayList<BodyItemstypeWeapon>? = null,
    var weaponsEnchantedReplicas: ArrayList<BodyItemstypeWeapon>? = null,
    var weaponsChargedReplicas: ArrayList<BodyItemstypeWeapon>? = null,
)

data class BodyItemstypeWeapon(
    var name: String? = null,
    var image: String? = null,
    var level:String? = null,
    var damage: String? = null,
    var damageType: DamageType? = null,
    var attack: String? = null,
    var defense: String? = null,
    var defMode: String? = null,
    var hands:String? = null,
    var resist: String? = null,
    var mana: String? = null,
    var slots:String? = null,
    var classs:String? = null,
    var weight: String? = null,
    var attributes: String? = null,
    val range: String? = null,
    val atkMode: String? = null,
    val hit: String? = null,
    val embuimentSlots: String? = null,
    val atk: String? = null,
    val npcPrice: String? = null
)

data class DamageType(
    var damageName: String? = null,
    var imageIcon: String? = null
)

//Household Items models
data class HouseHoldModel(
    val statusCode:Int? = null,
    val body: ArrayList<HouseHold>? = null
)

data class HouseHold(
    val name: String? = null,
    val img: String? = null,
    val price: String? = null,
    val vol: String? = null,
    val weight: String? = null,
    val slots: String? = null,
    val weightPerVol: String? = null,
    val buyFrom: String? = null,
    val light: String? = null,
    val writable: String? = null
)

