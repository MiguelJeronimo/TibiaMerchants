package com.miguel.tibiamerchants.domain.models.navigation

import com.miguel.tibiamerchants.R

enum class NavigationMain (
    val route: String,
    val label: String,
    val icon: Int,
    val contentDescription: String
){
    NPCDefaultFragment(route = "NPCDefaultFragment", label = "NPCs", icon = R.drawable.rashid, contentDescription = "NPCs"),
    TCPrices(route = "TCPrices", label = "TC Prices", icon = R.drawable.tibia_coins_escapet_150x150, contentDescription = "TC Prices"),
    TibiaTrade(route = "TibiaTrade", label = "Character trade", icon = R.drawable.trade_icon_png, contentDescription = "Character trade")
}