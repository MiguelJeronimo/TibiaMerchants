package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells
import com.miguel.tibiamerchants.domain.usecases.UseCaseSpellList
import kotlinx.coroutines.launch

class ViewModelSpells(private val useCase: UseCaseSpellList) : ViewModel() {
    private val _spells = MutableLiveData<ResponseSpells>()
    val spells: MutableLiveData<ResponseSpells> = _spells

    val _progress = MutableLiveData<Boolean>()
    val progress: MutableLiveData<Boolean> = _progress

    private val _isBack = MutableLiveData<Boolean>()
    val isBack: MutableLiveData<Boolean>get() = _isBack

    init {
        _progress.value = true
        viewModelScope.launch {
            _spells.value = useCase.spells()
        }
    }

    fun setBack(value: Boolean){
        _isBack.value = value
    }

    fun setSpells(){
        viewModelScope.launch { _spells.value = useCase.spells() }
    }

    fun isProgress(value: Boolean){
        _progress.value = value
    }
}