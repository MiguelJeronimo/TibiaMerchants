package com.miguel.tibiamerchants.domain.models

import com.google.gson.annotations.SerializedName

data class PriceTcModel(
    val prices: List<PriceModel>
)

/***
 *             "world_name": "Astera",
 *             "buy_average_price": 38860,
 *             "buy_highest_price": 40700,
 *             "sell_lowest_price": 37605,
 *             "sell_average_price": 40419,
 *             "created_at": "2025-07-25T10:52:03.342Z"
 * */
data class PriceModel(
    @SerializedName("world_name")
    val worldName:String,
    @SerializedName("buy_average_price")
    val buyAveragePrice:Int,
    @SerializedName("buy_highest_price")
    val buyHighestPrice:Int,
    @SerializedName("sell_lowest_price")
    val sellLowestPrice:Int,
    @SerializedName("sell_average_price")
    val sellAveragePrice:Int,
    @SerializedName("created_at")
    val createdAt:String
)