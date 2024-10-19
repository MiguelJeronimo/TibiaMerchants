package com.miguel.tibiamerchants.presentation.viewmodelproviders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.usecases.UseCaseItemsCatalog
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelItems

class ViewModelItemsFactory(private val useCaseItemsCatalog: UseCaseItemsCatalog)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelItems::class.java)) {
            return ViewModelItems(useCaseItemsCatalog) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}