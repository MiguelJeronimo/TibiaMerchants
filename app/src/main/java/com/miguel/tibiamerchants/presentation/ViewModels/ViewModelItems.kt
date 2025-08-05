package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibiamerchants.domain.models.ItemsModels
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.usecases.UseCaseItemsCatalog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelItems(private val useCaseItemsCatalog: UseCaseItemsCatalog) : ViewModel() {
    private val _items = MutableStateFlow<UiState>(UiState())
    val items: StateFlow<UiState> = _items

    private val _isBack = MutableLiveData<Boolean>()
    val isBack: MutableLiveData<Boolean>get() = _isBack


    init {
        viewModelScope.launch {
            _items.value = UiState(_isLoading = true)
            val result = useCaseItemsCatalog.items()
            result.onSuccess {
                _items.value = UiState(items = it)
            }
            result.onFailure {
                _items.value = UiState(error = it.message)
            }
        }
    }

    fun setItems() {
        viewModelScope.launch {
            val result = useCaseItemsCatalog.items()
            result.onSuccess {
                _items.value = UiState(items = it)
            }
            result.onFailure {
                _items.value = UiState(error = it.message)
            }
        }
    }
    fun setBack(status:Boolean){
        _isBack.value = status
    }

    data class UiState(
        val _isLoading: Boolean = false,
        val items: ItemsModels? = null,
        val error: String? = null
    )

}