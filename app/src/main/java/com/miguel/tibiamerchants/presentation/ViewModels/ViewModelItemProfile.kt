package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibia_merchants_api.domain.models.ResponseItemProfile
import com.miguel.tibiamerchants.domain.usecases.UseCaseIItemProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelItemProfile(private val useCaseItemProfile: UseCaseIItemProfile): ViewModel() {

    private val _itemProfile = MutableStateFlow(UIState())
    val itemProfile: StateFlow<UIState> = _itemProfile

    fun setItemProfiel(name:String){
        viewModelScope.launch {
           _itemProfile.value = UIState(_isLoading = true)
            val response = useCaseItemProfile.item(name)
            response.onSuccess {
                _itemProfile.value = UIState(itemProfile = it)
            }
            response.onFailure {
                _itemProfile.value = UIState(error = it.message)
            }
        }
    }


    data class UIState(
        val _isLoading: Boolean = false,
        val error: String? = null,
        val itemProfile: ResponseItemProfile? = null
    )

}