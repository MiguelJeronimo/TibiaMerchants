package com.miguel.tibiamerchants.domain.models.navigation

import android.net.Uri
import com.miguel.tibiamerchants.R

enum class NavigationItemsDetails (
    val route: String,
    val label: String,
    val icon: Int,
    val contentDescription: String
) {
    Items(
        route = "Items",
        label = "Items",
        icon = R.drawable.tibia_coins_escapet_150x150,
        contentDescription = "TC Prices"
    ),
    ItemDetails(
        route = "ItemDetails/{itemName}",
        label = "Item Details",
        icon = R.drawable.trade_icon_png,
        contentDescription = "Character trade"
    );

    companion object {
        fun itemDetailsRouteWithName(name: String): String {
            return "ItemDetails/${Uri.encode(name)}"
        }
    }
}