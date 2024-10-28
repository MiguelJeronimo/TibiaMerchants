package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibia_merchants_api.domain.models.ResponseItemProfile

interface RepositoryItemsProfile {
    suspend fun item(name:String): ResponseItemProfile?
}