package com.miguel.tibiamerchants.presentation.ViewModels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibia_merchants_api.domain.models.ResponseItemProfile
import com.miguel.tibiamerchants.domain.usecases.UseCaseIItemProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelItemProfile(private val useCaseItemProfile: UseCaseIItemProfile, savableStateHandle: SavedStateHandle): ViewModel() {

    private val _itemProfile = MutableStateFlow(UIState())
    val itemProfile: StateFlow<UIState> = _itemProfile

    init {
        val name = savableStateHandle.get<String>("itemName")
        Log.d("ViewModelItemProfile", "name: $name")
        if (name != null) {
            setItemProfiel(name)
        } else{
            setItemProfiel("")
        }
    }

    fun setItemProfiel(name:String){
        if (name.isEmpty())return
        viewModelScope.launch {
           _itemProfile.value = UIState(_isLoading = true)
            val response = useCaseItemProfile.item(name)
            Log.d("ViewModelItemProfile", "response: $response")
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