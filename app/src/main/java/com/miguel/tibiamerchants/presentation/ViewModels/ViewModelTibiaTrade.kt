package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibiamerchants.domain.UseCaseTibiaTrade
import com.miguel.tibiamerchants.domain.models.PriceTcModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelTibiaTrade(private val useCaseTibiaTrade: UseCaseTibiaTrade): ViewModel() {
    private val _tcPrice = MutableStateFlow<PriceTcModel?>(null)
    val tcPrice: StateFlow<PriceTcModel?> = _tcPrice
    fun tcPrice(){
        viewModelScope.launch {
            _tcPrice.value = useCaseTibiaTrade.getTcPrice()
        }
    }
}