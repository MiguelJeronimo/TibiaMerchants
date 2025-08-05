package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibiamerchants.domain.usecases.UseCaseNPC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.Tibia.NPCModel


class ViewModelNPC(private val useCase: UseCaseNPC): ViewModel() {
    private val _npcInformation = MutableStateFlow<UIState>(UIState())
    val npcInformation: StateFlow<UIState> = _npcInformation
    private val _isBack = MutableLiveData(false)
    val isBack: LiveData<Boolean> = _isBack

    fun back(state:Boolean){
        _isBack.value = state
    }

    init {
        _npcInformation.value = UIState(isLoading = true)
        viewModelScope.launch {
            val response = useCase.npc("Rashid")
            response.onSuccess {
                _npcInformation.value = UIState(isLoading = false, npc = it)
            }
            response.onFailure {
                _npcInformation.value = UIState(isLoading = false, error = it.message)
            }
        }
    }

    fun setNPCName(name: String){
        _npcInformation.value = UIState(isLoading = true)
        viewModelScope.launch {
            val response = useCase.npc(name)
            response.onSuccess {
                _npcInformation.value = UIState(isLoading = false, npc = it)
            }
            response.onFailure {
                _npcInformation.value = UIState(isLoading = false, error = it.message)
            }
        }
    }

    data class UIState(
        val isLoading: Boolean = false,
        val npc: NPCModel? = null,
        val error: String? = null
    )
}