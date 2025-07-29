package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.data.network.retrofit.ApiTibiaTradeClient
import com.miguel.tibiamerchants.domain.models.PriceTcModel

class RepositoryTibiaClientImpl(private val api: ApiTibiaTradeClient): RepositoryTibiaClient {
    override suspend fun getTcPrices(): PriceTcModel? {
        return api.tcPrices().body()
    }
}