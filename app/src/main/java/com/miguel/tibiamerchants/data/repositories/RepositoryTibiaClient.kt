package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.domain.models.PriceTcModel

interface RepositoryTibiaClient {
    suspend fun getTcPrices(): PriceTcModel?
}