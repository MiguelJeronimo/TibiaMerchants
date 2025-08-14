package com.miguel.tibiamerchants.domain.models.navigation

import android.net.Uri

enum class NavigationTibiaTrade(
    val route: String,
    val label: String,
    val icon: Int,
    val contentDescription: String
) {
    TibiaTradeFragment(
        route = "TibiaTradeFragment",
        label = "Tibia Trade",
        icon = 1,
        contentDescription = "Character trade"
    ),
    TibiaTradeProfile(
        route = "TibiaTradeProfile/{userName}",
        label = "Tibia Trade Profile",
        icon = 1,
        contentDescription = "Character trade profile"
    );

    companion object {
        fun routeWithName(userName: String): String {
            return "TibiaTradeProfile/${Uri.encode(userName)}"
        }
    }
}