package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibiamerchants.domain.models.vocations.Vocation
import com.miguel.tibiamerchants.domain.models.vocations.Vocations
import com.miguel.tibiamerchants.domain.usecases.UseCaseVocations
import kotlinx.coroutines.launch

class ViewModelVocations(private val useCase: UseCaseVocations): ViewModel(){
    private val _vocations = MutableLiveData<Vocations>()
    val vocations: LiveData<Vocations> get() = _vocations
    private val _vocation = MutableLiveData<Vocation>()
    val vocation: LiveData<Vocation> get() = _vocation

    private val _back = MutableLiveData<Boolean>()
    val back: LiveData<Boolean> get() = _back

    fun setBack(back: Boolean){
        _back.value = back
    }

    init {
        viewModelScope.launch { _vocations.value = useCase.vocations()  }
    }

    fun vocation(vocationName: String){
        viewModelScope.launch {
            _vocation.value = useCase.vocation(vocations = vocationName)
        }
    }
}