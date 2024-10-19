package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells
import com.miguel.tibiamerchants.domain.models.spells.Spells

interface RepositorySpells {
    suspend fun spellsList(): ResponseSpells?
}