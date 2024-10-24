package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelNPCS: ViewModel() {

    private val _npcName = MutableLiveData<String?>()
    val npc: MutableLiveData<String?> get() = _npcName
    private val _stateAbout = MutableLiveData<Boolean>()
    val stateAbout: MutableLiveData<Boolean> get() = _stateAbout

    private val _stateItems = MutableLiveData<Boolean>()
    val stateItems: MutableLiveData<Boolean> get() = _stateItems

    private val _stateSpells = MutableLiveData<Boolean>()
    val stateSpells: MutableLiveData<Boolean> get() = _stateSpells

    init{
        _npcName.value = null
        _stateAbout.value = false
    }
    fun setNPCName(name: String?){
        _npcName.value = name
    }
    fun setAboutState(state: Boolean){
        _stateAbout.value = state
    }
    fun setItemsState(state: Boolean){
        _stateItems.value = state
    }

    fun setSpellsState(state: Boolean){
        _stateSpells.value = state
    }

}