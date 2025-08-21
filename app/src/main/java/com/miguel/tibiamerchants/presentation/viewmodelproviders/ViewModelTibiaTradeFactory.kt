package com.miguel.tibiamerchants.presentation.viewmodelproviders

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.usecases.UseCaseTibiaTrade
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelTibiaTrade

class ViewModelTibiaTradeFactory (val useCaseTibiaTrade: UseCaseTibiaTrade, private val savableStateHandle: SavedStateHandle): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelTibiaTrade::class.java)){
            return ViewModelTibiaTrade(useCaseTibiaTrade, savableStateHandle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}