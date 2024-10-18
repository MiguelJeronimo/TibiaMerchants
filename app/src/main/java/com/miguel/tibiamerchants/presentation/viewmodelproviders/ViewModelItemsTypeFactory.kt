package com.miguel.tibiamerchants.presentation.viewmodelproviders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.usecases.UseCaseItemsType
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModeltemsType

class ViewModelItemsTypeFactory(private val useCaseItemsType: UseCaseItemsType): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModeltemsType::class.java)) {
            return ViewModeltemsType(useCaseItemsType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}