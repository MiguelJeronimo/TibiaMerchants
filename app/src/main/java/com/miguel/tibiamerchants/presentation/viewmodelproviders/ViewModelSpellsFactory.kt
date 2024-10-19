package com.miguel.tibiamerchants.presentation.viewmodelproviders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.usecases.UseCaseSpellList
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelSpells

class ViewModelSpellsFactory(private val useCase: UseCaseSpellList): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelSpells::class.java)){
            return ViewModelSpells(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModelSpells class")
    }
}