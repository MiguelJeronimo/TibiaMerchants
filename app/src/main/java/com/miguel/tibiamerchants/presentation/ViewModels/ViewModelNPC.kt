package com.miguel.tibiamerchants.presentation.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibiamerchants.domain.usecases.UseCaseNPC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.Tibia.NPCModel


class ViewModelNPC(private val useCase: UseCaseNPC, private val savableStateHandle: SavedStateHandle): ViewModel() {
    private val _npcInformation = MutableStateFlow<UIState>(UIState())
    val npcInformation: StateFlow<UIState> = _npcInformation
    private val _isBack = MutableLiveData(false)
    val isBack: LiveData<Boolean> = _isBack

    private val _npcName = savableStateHandle.getStateFlow(NPC_NAME, "")

    private companion object{
        const val NPC_NAME = "npc_name"
    }

    init {
        viewModelScope.launch {
            _npcName.collect { name ->
               if (name.isNotEmpty()){
                   getNPC(name)
               } else{
                   getNPC("")
               }
            }
        }
    }

    fun setNPCName(name: String){
        Log.d("ViewModel", "setNPCName: $name")
        savableStateHandle[NPC_NAME] = name
    }

    fun back(state:Boolean){
        _isBack.value = state
    }

    fun getNPC(name: String){
        if (name.isEmpty()) return
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