package com.miguel.tibiamerchants.domain.models

import com.google.gson.annotations.SerializedName


data class ItemsModels(
    val statusCode:Int? = null,
    val body: Body? = null
)
data class Body(
    @SerializedName("body_equipment")
    val bodyEquipment: BodyEquipment,
    val weapons: WeaponsEquipments,
    @SerializedName("household_items")
    val householdItems: HouseholdItems,
    val others: Others,
    @SerializedName("tools_equipment")
    val toolsEquipment: ToolsEquipment,
    @SerializedName("other_items")
    val otherItems: OtherItems
)

data class BodyEquipment(
    val title:String,
    val array: ArrayList<Data>
)

data class WeaponsEquipments(
    val title:String,
    val array: ArrayList<Data>
)

data class HouseholdItems(
    val title:String,
    val array: ArrayList<Data>
)

data class Others(
    val title:String,
    val array: ArrayList<Data>
)

data class ToolsEquipment(
    val title:String,
    val array: ArrayList<Data>
)

data class OtherItems(
    val title:String,
    val array: ArrayList<Data>
)

data class Data(
    val name:String,
    val img:String
)



