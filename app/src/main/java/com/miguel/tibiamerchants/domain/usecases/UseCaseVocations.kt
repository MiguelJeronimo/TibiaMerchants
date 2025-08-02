package com.miguel.tibiamerchants.domain.usecases

import com.miguel.tibiamerchants.data.repositories.VocationRepository

class UseCaseVocations(private val repository: VocationRepository) {
    suspend fun vocations() = repository.vocations()
    suspend fun vocation(vocations: String) = repository.vocation(vocations)
}
