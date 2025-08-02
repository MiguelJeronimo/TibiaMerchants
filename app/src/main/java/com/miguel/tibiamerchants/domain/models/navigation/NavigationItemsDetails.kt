package com.miguel.tibiamerchants.domain.models.navigation

import com.miguel.tibiamerchants.R

enum class NavigationItemsDetails (
    val route: String,
    val label: String,
    val icon: Int,
    val contentDescription: String
){
    ItemsCatalog(route = "ItemsCatalog", label = "Items", icon = R.drawable.rashid, contentDescription = "NPCs"),
    Items(route = "Items", label = "Items", icon = R.drawable.tibia_coins_escapet_150x150, contentDescription = "TC Prices"),
    ItemDetails(route = "ItemDetails", label = "Item Details", icon = R.drawable.trade_icon_png, contentDescription = "Character trade")
}