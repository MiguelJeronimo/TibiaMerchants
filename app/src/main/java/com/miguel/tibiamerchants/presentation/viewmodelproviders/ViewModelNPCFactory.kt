package com.miguel.tibiamerchants.presentation.viewmodelproviders

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.usecases.UseCaseNPC
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelNPC

class ViewModelNPCFactory(private val useCase: UseCaseNPC, private val savableStateHandle: SavedStateHandle): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelNPC::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelNPC(useCase, savableStateHandle) as T
        }
        throw IllegalArgumentException("Unknown ViewModelNPC class")
    }
}