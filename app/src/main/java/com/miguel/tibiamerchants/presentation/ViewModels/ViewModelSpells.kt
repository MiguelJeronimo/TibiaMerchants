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

    init {
        viewModelScope.launch {
            _spells.value = useCase.spells()
        }
    }
}