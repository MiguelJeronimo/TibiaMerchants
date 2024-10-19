package com.miguel.tibiamerchants.domain.models.spells

data class ResponseSpells(
    val statusCode: Int? = null,
    val body: Spells? = null
)

data class Spells(
    var spells: ArrayList<Spell>? = null,
    var runes: ArrayList<Runes>? = null
)

data class Spell(
    var name:String? = null,
    var img:String? = null,
    var formula:String? = null,
    var premium:String? = null,
    var level:String? = null,
    var mana:String? = null,
    var price:String? = null,
    var group:String? = null,
    var effect:Effect? = null
)

data class Runes(
    var name:String? = null,
    var img:String? = null,
    var formula: String? = null,
    var premium:String? = null,
    var level:String? = null,
    var soul_points:String? = null,
    var Amount:String? = null,
    var mana:String? = null,
    var price:String? = null,
    var rune_group:String? = null,
    var effect:Effect? = null
)

data class Effect(
    var description: String? = null,
    var img: String? = null
)