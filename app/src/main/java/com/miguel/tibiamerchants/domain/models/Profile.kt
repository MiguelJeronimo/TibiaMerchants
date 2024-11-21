package com.miguel.tibia_merchants_api.domain.models

import com.google.gson.annotations.SerializedName


data class ResponseItemProfile(
    val statusCode: Int? = null,
    val body: Profile? = null
)

data class Profile(
    var name: String? = null,
    var img: String? = null,
    @SerializedName("tibia_lengend")
    var tibiaLengend: String? = null,
    var notes: String? = null,
    var requeriments: Requeriments? = null,
    @SerializedName("combat_propierties")
    var combatPropierties: CombatPropierties? = null,
    @SerializedName("general_propierties")
    var generalPropierties: GeneralPropierties? = null,
    @SerializedName("trader_propierties")
    var traderPropierties: TraderPropierties? = null,
    @SerializedName("magic_properties")
    var magicProperties: MagicProperties? = null,
    @SerializedName("field_propierties")
    var fieldPropierties: FieldPropierties? = null,
    @SerializedName("other_propierties")
    var otherPropierties: OtherPropierties? = null,
    @SerializedName("buy_from")
    var buyFrom: ArrayList<BuyFrom>? = null,
    @SerializedName("sell_from")
    var sellFrom: ArrayList<SellFrom>? = null
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