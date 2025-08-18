package com.miguel.tibiamerchants.presentation.ViewModels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.miguel.tibiamerchants.domain.models.Profile
import com.miguel.tibiamerchants.domain.models.TibiaTradeItemModel
import com.miguel.tibiamerchants.domain.usecases.UseCaseTibiaTrade
import com.miguel.tibiamerchants.domain.models.TibiaTradeModel
import com.miguel.tibiamerchants.domain.models.Trade
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModelTibiaTrade(private val useCaseTibiaTrade: UseCaseTibiaTrade, savableStateHandle: SavedStateHandle): ViewModel() {
    private val _tibiaTrade = MutableStateFlow<UIState>(UIState())
    val tibiaTrade: StateFlow<UIState> = _tibiaTrade
    val items: Flow<PagingData<Trade>> = useCaseTibiaTrade.getItems().cachedIn(viewModelScope)
    private val _profile = MutableStateFlow<UIStateProfile>(UIStateProfile())
    val profile: StateFlow<UIStateProfile> = _profile

    private val _item = MutableStateFlow<UIStateItem>(UIStateItem())
    val item: StateFlow<UIStateItem> = _item
    init {
        val userName  = savableStateHandle.get<String>("userName")
        if (userName != null) {
            profile(userName)
        } else {
            profile("")
        }

        val itemId = savableStateHandle.get<String>("id")
        Log.d("ViewModel", "SavedStateHandle: $itemId")
        if (itemId != null) {
            item(itemId.toInt())
        } else {
            item(null)
        }
    }

    fun profile(userName: String){
        if (userName.isBlank()) {
            _profile.update { it.copy(error = "User name is blank", isLoding = false) }
            return
        }
        viewModelScope.launch {
            _profile.update { it.copy(isLoding = true, error = null) }
            val result = useCaseTibiaTrade.profile(userName)
            result.onSuccess {data->
                Log.d("ViewModel Profile", "profile: $data")
                _profile.update { it.copy(data = data, isLoding = false, error = null) }
            }
            result.onFailure {err->
                Log.d("ViewModel Profile", "Error: $err")
                _profile.update { it.copy(error = err.message, isLoding = false) }
            }
        }
    }

    fun item(id: Int?){
        if (id == null) {
            _item.update { it.copy(error = "Item id is blank", isLoding = false) }
            return
        }
        viewModelScope.launch {
            Log.d("ViewModel Item", "item: $id")
            _item.update { it.copy(isLoding = true, error = null) }
            val result = useCaseTibiaTrade.getTradeById(id)
            result.onSuccess {data->
                _item.update { it.copy(data = data, isLoding = false, error = null) }
            }
            result.onFailure {err->
                _item.update { it.copy(error = err.message, isLoding = false) }
            }
        }
    }

    data class UIStateProfile(
        val isLoding: Boolean = false,
        val data: Profile? = null,
        val error: String? = null
    )

    data class UIStateItem(
        val isLoding: Boolean = false,
        val data: TibiaTradeItemModel? = null,
        val error: String? = null
    )

    data class UIState(
        val isLoding: Boolean = false,
        val data: TibiaTradeModel? = null,
        val error: String? = null
    )
}