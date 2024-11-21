package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibia_merchants_api.domain.models.ResponseItemProfile
import com.miguel.tibiamerchants.domain.usecases.UseCaseIItemProfile
import kotlinx.coroutines.launch

class ViewModelItemProfile(private val useCaseItemProfile: UseCaseIItemProfile): ViewModel() {

    private val _itemProfile = MutableLiveData<ResponseItemProfile>()
    val itemProfile: MutableLiveData<ResponseItemProfile> = _itemProfile

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    fun setItemProfiel(name:String){
        viewModelScope.launch {
            _itemProfile.value = useCaseItemProfile.item(name)
        }
    }

    fun loading(state: Boolean){
        _isLoading.value = state
    }

}