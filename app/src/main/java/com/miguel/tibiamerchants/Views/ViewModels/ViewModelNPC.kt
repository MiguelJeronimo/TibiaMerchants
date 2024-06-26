package com.miguel.tibiamerchants.Views.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.tibiamerchants.Repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.Tibia.NPC

class ViewModelNPC: ViewModel() {
    private val repository = Repository()
    private val _npcInformation = MutableLiveData<NPC?>()
    val npcInformation: MutableLiveData<NPC?>get() = _npcInformation

    private val _isBack = MutableLiveData<Boolean>()
    val isBack: MutableLiveData<Boolean>get() = _isBack

    private val _isVisibleProgressBar = MutableLiveData<Boolean>()
    val isVisibleProgressBar: MutableLiveData<Boolean>get() = _isVisibleProgressBar

    init {
        _isVisibleProgressBar.value = true
    }

    private val uiScope = CoroutineScope(Dispatchers.Main)
    fun setNpcInformation(name: String?) {
        uiScope.launch {
            withContext(Dispatchers.IO){
               val response =  when(name){
                    "Rashid"->{repository.rashid()}
                    "Yasir"->{repository.yasir()}
                    "Haroun"->{repository.horoun()}
                    "Nah'Bob"->{repository.nashBob()}
                    "Asnarus"->{repository.asnarus()}
                    "Alesar"->{repository.alesar()}
                    "Yaman"->{repository.yalam()}
                    "Esrik"->{repository.esrik()}
                    "Alexander"->{repository.alexander()}
                    "Tamoril"->{repository.tamoril()}
                    "Grizzly Adams"->{repository.grizzlyAdams()}
                    else -> {
                        println("Se fue al null")
                        null
                    }
                }
                _npcInformation.postValue(response)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        uiScope.cancel()
    }

    fun setBack(status:Boolean){
        _isBack.value = status
    }

    fun setProgressBar(status:Boolean){
        _isVisibleProgressBar.value = status
    }

}