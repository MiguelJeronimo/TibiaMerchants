package com.miguel.tibia_merchants_api.domain.models


data class ResponseItemProfile(
    val statusCode: Int? = null,
    val body: Profile? = null
)

data class Profile(
    var name: String? = null,
    var img: String? = null,
    var tibia_lengend: String? = null,
    var notes: String? = null,
    var requeriments: Requeriments? = null,
    var combat_propierties: CombatPropierties? = null,
    var general_propierties: GeneralPropierties? = null,
    var trader_propierties: TraderPropierties? = null,
    var magic_properties: MagicProperties? = null,
    var field_propierties: FieldPropierties? = null,
    var other_propierties: OtherPropierties? = null,
    var buy_from: ArrayList<BuyFrom>? = null,
    var sell_from: ArrayList<SellFrom>? = null
)

data class BuyFrom(
    var npc: String? = null,
    var location: String? = null,
    var price: String? = null
)

data class SellFrom(
    var npc: String? = null,
    var location: String? = null,
    var price: String? = null
)

data class Requeriments(
    var level: String? = null,
    var vocation: String? = null,
    var magic_level: String? = null
)

data class CombatPropierties(
    var imbuing_slots: String? = null,
    var upgrade_classification: String? = null,
    var attributes: String? = null,
    var armor: String? = null,
    var resists: String? = null,
    var element: String? = null
)

data class GeneralPropierties(
    var classification: String? = null,
    var also_known_as:String ? = null,
    var item_class: String? = null,
    var origin:String?=null,
    var notes:String? = null,
    var pickupable: String? = null,
    var weight: String? = null,
    var stackable: String? = null,
)

data class TraderPropierties(
    var marketable: String? = null,
    var value: String? = null,
    var sold_for: String? = null,
    var bought_for: String? = null,
    var loot_value: String? = null,
    var store_price: String? = null,
)

data class FieldPropierties(
    var blocking: String? = null,
    var light: String? = null
)

data class OtherPropierties(
    var version: String? = null
)

data class MagicProperties(
    var words: String? = null
)

data class Sections(
    var key: String? = null,
    var value: String? = null
)