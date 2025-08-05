package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibiamerchants.domain.models.PriceTcModel
import com.miguel.tibiamerchants.domain.usecases.UseCaseTibiaTrade
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelTCPrice(private val useCaseTibiaTrade: UseCaseTibiaTrade): ViewModel() {
    private val _tcPrice = MutableStateFlow<UIState>(UIState())
    val tcPrice: StateFlow<UIState> = _tcPrice

    init {
        viewModelScope.launch {
            _tcPrice.value = UIState(isLoding = true)
            val result = useCaseTibiaTrade.getTcPrice()
            result.onSuccess {
                _tcPrice.value = UIState(data = it)
            }
            result.onFailure {
                _tcPrice.value = UIState(error = it.message)
            }
        }
    }

    fun getTCPrice(){
        viewModelScope.launch {
            _tcPrice.value = UIState(isLoding = true)
            val result = useCaseTibiaTrade.getTcPrice()
            result.onSuccess {
                _tcPrice.value = UIState(data = it)
            }
            result.onFailure {
                _tcPrice.value = UIState(error = it.message)
            }
        }
    }

    data class UIState(
        val isLoding: Boolean = false,
        val data: PriceTcModel? = null,
        val error: String? = null
    )

}