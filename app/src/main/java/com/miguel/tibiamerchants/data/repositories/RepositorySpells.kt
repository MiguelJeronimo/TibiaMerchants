package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells

interface RepositorySpells {
    suspend fun spellsList(): ResponseSpells?
}