package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibiamerchants.domain.models.HouseHoldModel
import com.miguel.tibiamerchants.domain.models.ItemsModelsType
import com.miguel.tibiamerchants.domain.models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.domain.models.OtherItemsModel
import com.miguel.tibiamerchants.domain.models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.ToolsAndOtherEquipmentModel
import com.miguel.tibiamerchants.domain.usecases.UseCaseItemsType
import kotlinx.coroutines.launch

class ViewModeltemsType(private val useCaseItemsType: UseCaseItemsType) : ViewModel() {
    //private val repository = RepositoryItems()

    private val _items = MutableLiveData<ItemsModelsType>()
    val items: MutableLiveData<ItemsModelsType> = _items

    private val _itemsTypeWeapons = MutableLiveData<ItemsModelsTypeWeapons>()
    val itemsTypeWeapons: MutableLiveData<ItemsModelsTypeWeapons> = _itemsTypeWeapons

    private val _itemsTypeHouseHold = MutableLiveData<HouseHoldModel>()
    val itemsTypeHouseHold: MutableLiveData<HouseHoldModel> = _itemsTypeHouseHold

    private val _plantsAnimalsProductsFoodDrink = MutableLiveData<PlantsAnimalsProductsFoodDrink>()
    val plantsAnimalsProductsFoodDrink: MutableLiveData<PlantsAnimalsProductsFoodDrink> get() = _plantsAnimalsProductsFoodDrink

    private val _itemsTypeToolsAndOthers = MutableLiveData<ToolsAndOtherEquipmentModel>()
    val itemsTypeToolsAndOthers: MutableLiveData<ToolsAndOtherEquipmentModel> get() = _itemsTypeToolsAndOthers

    private val _itemsTypeOtherItems = MutableLiveData<OtherItemsModel>()
    val itemsTypeOtherItems: MutableLiveData<OtherItemsModel> get() = _itemsTypeOtherItems

    private val _back = MutableLiveData<Boolean>()
    val back: MutableLiveData<Boolean> = _back

    private val _name = MutableLiveData<String>()
    val name: MutableLiveData<String> = _name

    fun setName(name: String?){
        this.name.value = name
    }

    fun setBack(state: Boolean){
        _back.value = state
    }

    private val _isVisibleProgressBar = MutableLiveData<Boolean>()
    val isVisibleProgressBar: MutableLiveData<Boolean> = _isVisibleProgressBar
    init {
        _isVisibleProgressBar.value = true
    }
    fun setProgressBar(state: Boolean){
        _isVisibleProgressBar.value = state
    }

    fun setItems(body: PostItemsType) {
        viewModelScope.launch {
            _items.value = useCaseItemsType.itemsType(body)
        }
    }

    fun setItemsWeapons(body: PostItemsType) {
        viewModelScope.launch {
            _itemsTypeWeapons.value = useCaseItemsType.itemsTypeWeapons(body)
        }
    }
    fun setItemsHouseHold(body: PostItemsType) {
        viewModelScope.launch {
            _itemsTypeHouseHold.value = useCaseItemsType.itemsTypeHouseHold(body)
        }
    }

    fun setPlantsAnimalsProductsFoodDrink(body: PostItemsType){
        viewModelScope.launch {
            _plantsAnimalsProductsFoodDrink.value = useCaseItemsType.itemsTypeOthers(body)
        }
    }

    fun setItemsToolsAndOthers(body: PostItemsType){
        viewModelScope.launch {
            _itemsTypeToolsAndOthers.value = useCaseItemsType.itemsTypeToolsAndOthers(body)
        }
    }
    fun setItemsOtherItems(body: PostItemsType){
        viewModelScope.launch {
            _itemsTypeOtherItems.value = useCaseItemsType.itemsTypeOtherItems(body)
        }
    }
}