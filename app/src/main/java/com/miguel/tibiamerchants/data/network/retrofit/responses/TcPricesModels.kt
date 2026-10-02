package com.miguel.tibiamerchants.data.network.retrofit.responses

import com.google.gson.annotations.SerializedName

/***
 *             "world_name": "Astera",
 *             "buy_average_price": 38860,
 *             "buy_highest_price": 40700,
 *             "sell_lowest_price": 37605,
 *             "sell_average_price": 40419,
 *             "created_at": "2025-07-25T10:52:03.342Z"
 * */
data class PriceModel(
    @SerializedName("worldName")
    val worldName:String,
    @SerializedName("buyAveragePrice")
    val buyAveragePrice:Int,
    @SerializedName("buyHighestPrice")
    val buyHighestPrice:Int,
    @SerializedName("sellLowestPrice")
    val sellLowestPrice:Int,
    @SerializedName("sellAveragePrice")
    val sellAveragePrice:Int,
    @SerializedName("createdAt")
    val createdAt:String
)