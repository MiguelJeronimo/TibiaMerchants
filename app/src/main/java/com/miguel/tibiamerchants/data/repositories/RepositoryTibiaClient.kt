package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.domain.models.PriceTcModel
import kotlinx.coroutines.flow.Flow

interface RepositoryTibiaClient {
    suspend fun getTcPrices(): PriceTcModel?
}