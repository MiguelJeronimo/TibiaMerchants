package com.miguel.tibiamerchants.domain.models.vocations

data class Vocations(
    val statusCode: Int,
    val body: ArrayList<VocationList>
)

data class VocationList(
    val name: String,
    val description: String,
)

data class Vocation(
    val statusCode: Int,
    val body: VocationInfo
)

data class VocationInfo(
    val description: String,
    val spells: ArrayList<VocationItems>,
    val spellsIsNotAvaible: ArrayList<VocationItems>,
    val runeSpells: ArrayList<VocationItems>
)

data class VocationItems(
    val name: String,
    val image: String,
    val formula: String,
    val premium: String,
    val level: String,
    val mana: String,
    val price: String,
    val group: String,
    val effect: String
)