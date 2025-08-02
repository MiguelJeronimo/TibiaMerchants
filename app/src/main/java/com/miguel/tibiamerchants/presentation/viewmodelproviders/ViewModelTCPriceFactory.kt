package com.miguel.tibiamerchants.presentation.viewmodelproviders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.usecases.UseCaseTibiaTrade
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelTCPrice

class ViewModelTCPriceFactory(private val useCaseTibiaTrade: UseCaseTibiaTrade): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewModelTCPrice::class.java)){
            return ViewModelTCPrice(useCaseTibiaTrade) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}