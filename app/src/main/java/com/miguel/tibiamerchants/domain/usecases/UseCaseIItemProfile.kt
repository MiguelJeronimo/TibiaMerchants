package com.miguel.tibiamerchants.domain.usecases

import com.miguel.tibia_merchants_api.domain.models.ResponseItemProfile
import com.miguel.tibiamerchants.data.repositories.RepositoryItemsProfile

class UseCaseIItemProfile(val repository: RepositoryItemsProfile) {

    suspend fun item(name:String): ResponseItemProfile? {
        return repository.item(name)
    }
}