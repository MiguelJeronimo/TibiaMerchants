package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.domain.models.vocations.Vocation
import com.miguel.tibiamerchants.domain.models.vocations.Vocations

interface VocationRepository {
    suspend fun vocations(): Vocations?
    suspend fun vocation(vocations: String): Vocation?
}