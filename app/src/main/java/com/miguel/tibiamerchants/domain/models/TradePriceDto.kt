package com.miguel.tibiamerchants.domain.models

import com.miguel.tibiamerchants.data.network.retrofit.responses.PriceModel

data class TradePriceDto(
    val worldName:String,
    val buyAveragePrice:Int,
    val buyHighestPrice:Int,
    val sellLowestPrice:Int,
    val sellAveragePrice:Int,
    val createdAt:String
)


fun List<PriceModel>.toTradePriceDto():List<TradePriceDto>{
    return map {
        TradePriceDto(
            worldName = it.worldName,
            buyAveragePrice = it.buyAveragePrice,
            buyHighestPrice = it.buyHighestPrice,
            sellLowestPrice = it.sellLowestPrice,
            sellAveragePrice = it.sellAveragePrice,
            createdAt = it.createdAt //Dates().format(date = it.createdAt).get()
        )
    }
}