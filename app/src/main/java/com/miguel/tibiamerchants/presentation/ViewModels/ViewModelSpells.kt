package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells
import com.miguel.tibiamerchants.domain.usecases.UseCaseSpellList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelSpells(private val useCase: UseCaseSpellList) : ViewModel() {
    private val _spells = MutableStateFlow(UIState())
    val spells: StateFlow<UIState> = _spells

    private val _isBack = MutableLiveData<Boolean>()
    val isBack: MutableLiveData<Boolean>get() = _isBack

    private val name = MutableLiveData<String>()
    val nameSpell: MutableLiveData<String> = name

    fun setNameSpell(nameSpell: String?){
        this.name.value = nameSpell
    }

    init {
        _spells.value = UIState(isLoading = true)
        viewModelScope.launch {
            _spells.value = useCase.spells().fold(
                onSuccess = { UIState(spells = it) },
                onFailure = { UIState(error = it.message) }
            )
        }
    }

    fun setBack(value: Boolean){
        _isBack.value = value
    }

    fun setSpellsRefresh(){
        viewModelScope.launch {
            _spells.value = useCase.spells().fold(
                onSuccess = { _spells.value.copy(spells = it, isLoading = false) },
                onFailure = { _spells.value.copy(error = it.message, isLoading = false) }
            )
        }
    }

    fun spells(){
        _spells.value = UIState(isLoading = true)
        viewModelScope.launch {
            _spells.value = useCase.spells().fold(
                onSuccess = { UIState(spells = it) },
                onFailure = { UIState(error = it.message) }
            )
        }
    }

    data class UIState(
        val isLoading: Boolean = false,
        val spells: ResponseSpells? = null,
        val error: String? = null
    )
}