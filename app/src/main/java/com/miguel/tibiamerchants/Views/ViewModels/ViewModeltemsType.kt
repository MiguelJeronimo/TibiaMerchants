package com.miguel.tibiamerchants.Views.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.tibiamerchants.Models.HouseHoldModel
import com.miguel.tibiamerchants.Models.ItemsModelsType
import com.miguel.tibiamerchants.Models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.Models.PostItemsType
import com.miguel.tibiamerchants.Repository.RepositoryItems

class ViewModeltemsType: ViewModel() {
    private val repository = RepositoryItems()
    private val _items = MutableLiveData<ItemsModelsType>()
    val items: MutableLiveData<ItemsModelsType> = _items
    private val _itemsTypeWeapons = MutableLiveData<ItemsModelsTypeWeapons>()
    val itemsTypeWeapons: MutableLiveData<ItemsModelsTypeWeapons> = _itemsTypeWeapons
    private val _itemsTypeHouseHold = MutableLiveData<HouseHoldModel>()
    val itemsTypeHouseHold: MutableLiveData<HouseHoldModel> = _itemsTypeHouseHold
    private val _isVisibleProgressBar = MutableLiveData<Boolean>()
    val isVisibleProgressBar: MutableLiveData<Boolean> = _isVisibleProgressBar
    init {
        _isVisibleProgressBar.value = true
    }
    fun setProgressBar(state: Boolean){
        _isVisibleProgressBar.value = state
    }

    fun setItems(body: PostItemsType) {
        repository.itemsType(_items, body)
    }

    fun setItemsWeapons(body: PostItemsType) {
        repository.itemsTypeWeapons(_itemsTypeWeapons, body)
    }
    fun setItemsHouseHold(body: PostItemsType) {
        repository.itemsTypeHouseHold(_itemsTypeHouseHold, body)
    }

}