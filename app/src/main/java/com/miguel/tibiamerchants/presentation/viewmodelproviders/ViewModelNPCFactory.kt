package com.miguel.tibiamerchants.presentation.viewmodelproviders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.usecases.UseCaseNPC
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelNPC

class ViewModelNPCFactory(private val useCase: UseCaseNPC): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelNPC::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelNPC(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModelNPC class")
    }
}