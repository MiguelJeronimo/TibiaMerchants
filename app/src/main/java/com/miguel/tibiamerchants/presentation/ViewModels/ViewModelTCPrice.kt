package com.miguel.tibiamerchants.presentation.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibiamerchants.domain.models.PriceTcModel
import com.miguel.tibiamerchants.domain.usecases.UseCaseTibiaTrade
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ViewModelTCPrice(private val useCaseTibiaTrade: UseCaseTibiaTrade) : ViewModel() {
    private val _tibiaCoin = MutableStateFlow<UIState>(UIState())
    val tibiaCoin: StateFlow<UIState> = _tibiaCoin
    private val _query = MutableStateFlow("")
    //filter information
    val tcPrice: StateFlow<UIState> = combine(_tibiaCoin, _query) { state, query ->
        if (query.isEmpty()) {
            state
        } else {
            val filter = state.data?.prices?.filter { it.worldName.contains(query, ignoreCase = true) }
            state.copy(data = state.data?.copy(prices = filter ?: emptyList()))
        }
    }.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        initialValue = UIState()
    )

    init {
        viewModelScope.launch {
            _tibiaCoin.value = UIState(isLoding = true)
            val result = useCaseTibiaTrade.getTcPrice()
            result.onSuccess {
                _tibiaCoin.value = UIState(data = it)
            }
            result.onFailure {
                _tibiaCoin.value = UIState(error = it.message)
            }
        }
    }
    fun getTCPrice() {
        viewModelScope.launch {
            _tibiaCoin.value = UIState(isLoding = true)
            val result = useCaseTibiaTrade.getTcPrice()
            result.onSuccess {
                _tibiaCoin.value = UIState(data = it)
            }
            result.onFailure {
                _tibiaCoin.value = UIState(error = it.message)
            }
        }
    }

    fun searchWorld(worldName: String) {
        _query.value = worldName
    }

    data class UIState(
        val isLoding: Boolean = false,
        val data: PriceTcModel? = null,
        val error: String? = null,
    )
}