package com.miguel.tibiamerchants.domain

import com.miguel.tibiamerchants.data.repositories.RepositoryTibiaClient
import com.miguel.tibiamerchants.domain.models.PriceTcModel

class UseCaseTibiaTrade(private val repository: RepositoryTibiaClient) {
    suspend fun getTcPrice(): PriceTcModel?{
        return repository.getTcPrices()
    }
}