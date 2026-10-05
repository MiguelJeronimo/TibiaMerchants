package com.miguel.tibiamerchants.domain.models

data class TradeItem(
    val id: Int,
    val itemId: Int,
    val itemTier: Int,
    val currencyType: Int,
    val type: Int
)