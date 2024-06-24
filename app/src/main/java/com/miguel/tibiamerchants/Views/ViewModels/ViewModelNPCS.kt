package com.miguel.tibiamerchants.Views.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelNPCS: ViewModel() {

    private val _npcName = MutableLiveData<String>()
    val npc: MutableLiveData<String> get() = _npcName
    private val _stateAbout = MutableLiveData<Boolean>()
    val stateAbout: MutableLiveData<Boolean> get() = _stateAbout

    fun setNPCName(name: String){
        _npcName.value = name
    }
    fun setAboutState(state: Boolean){
        _stateAbout.value = state
    }
}