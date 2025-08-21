package com.miguel.tibiamerchants.presentation.viewmodelproviders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.usecases.UseCaseVocations
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelVocations

class ViewModelVocationFactory(private val useCaseVocations: UseCaseVocations): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelVocations::class.java)) {
            return ViewModelVocations(useCaseVocations) as T
        }
        throw IllegalArgumentException("Unknown ViewModelSpells class")
    }
}