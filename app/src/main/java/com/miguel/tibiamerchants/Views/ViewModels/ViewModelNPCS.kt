package com.miguel.tibiamerchants.Views.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelNPCS: ViewModel() {

    private val _npcName = MutableLiveData<String>()
    val npc: MutableLiveData<String> get() = _npcName

    fun setNPCName(name: String){
        _npcName.value = name
    }
}