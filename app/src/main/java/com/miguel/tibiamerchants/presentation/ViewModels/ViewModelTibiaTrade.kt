package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.miguel.tibiamerchants.domain.usecases.UseCaseTibiaTrade
import com.miguel.tibiamerchants.domain.models.TibiaTradeModel
import com.miguel.tibiamerchants.domain.models.Trade
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModelTibiaTrade(private val useCaseTibiaTrade: UseCaseTibiaTrade): ViewModel() {
    private val _tibiaTrade = MutableStateFlow<UIState>(UIState())
    val tibiaTrade: StateFlow<UIState> = _tibiaTrade
    val items: Flow<PagingData<Trade>> = useCaseTibiaTrade.getItems().cachedIn(viewModelScope)

    init {
//        viewModelScope.launch {
//            _tibiaTrade.value = UIState(isLoding = true)
//            val result = useCaseTibiaTrade.getTrade(page= 0, sortType = 0)
//            result.onSuccess {
//                _tibiaTrade.value = UIState(data = it)
//            }
//            result.onFailure {
//                _tibiaTrade.value = UIState(error = it.message)
//            }
//        }
    }

    data class UIState(
        val isLoding: Boolean = false,
        val data: TibiaTradeModel? = null,
        val error: String? = null
    )
}