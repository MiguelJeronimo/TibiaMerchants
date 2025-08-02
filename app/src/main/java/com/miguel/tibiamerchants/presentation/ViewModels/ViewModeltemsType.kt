package com.miguel.tibiamerchants.presentation.ViewModels

import android.util.Log
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModeltemsType(private val useCaseItemsType: UseCaseItemsType) : ViewModel() {
    //private val repository = RepositoryItems()

    private val _items = MutableStateFlow(UIState())
    val items: StateFlow<UIState> = _items

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
            _items.value = UIState(isLoading = true)
            val response = useCaseItemsType.itemsType(body)
            response.onSuccess {
                Log.d("ITEMS", it.toString())
                _items.value = UIState(items = it)
            }
            response.onFailure {
                Log.d("ERROR", it.message.toString())
                _items.value = UIState(error = it.message)
            }
        }
    }

    fun setItemsWeapons(body: PostItemsType) {
        viewModelScope.launch {
            _items.value = UIState(isLoading = true)
            val response = useCaseItemsType.itemsTypeWeapons(body)
            response.onSuccess {
                _items.value = UIState(itemsTypeWeapons = it)
            }
            response.onFailure {
                _items.value = UIState(error = it.message)
            }
        }
    }
    fun setItemsHouseHold(body: PostItemsType) {
        viewModelScope.launch {
            _items.value = UIState(isLoading = true)
            val response = useCaseItemsType.itemsTypeHouseHold(body)
            response.onSuccess {
                _items.value = UIState(itemsTypeHouseHold = it)
            }
            response.onFailure {
                _items.value = UIState(error = it.message)
            }
        }
    }

    fun setPlantsAnimalsProductsFoodDrink(body: PostItemsType){
        viewModelScope.launch {
            _items.value = UIState(isLoading = true)
            val response = useCaseItemsType.itemsTypeOthers(body)
            response.onSuccess {
                _items.value = UIState(plantsAnimalsProductsFoodDrink = it)
            }
            response.onFailure {
                _items.value = UIState(error = it.message)
            }
        }
    }

    fun setItemsToolsAndOthers(body: PostItemsType){
        viewModelScope.launch {
            _items.value = UIState(isLoading = true)
            val response = useCaseItemsType.itemsTypeToolsAndOthers(body)
            response.onSuccess {
                _items.value = UIState(itemsTypeToolsAndOthers = it)
            }
            response.onFailure {
                _items.value = UIState(error = it.message)
            }
        }
    }
    fun setItemsOtherItems(body: PostItemsType){
        viewModelScope.launch {
            _items.value = UIState(isLoading = true)
            val response = useCaseItemsType.itemsTypeOtherItems(body)
            response.onSuccess {
                _items.value = UIState(itemsTypeOtherItems = it)
            }
            response.onFailure {
                _items.value = UIState(error = it.message)
            }
        }
    }

    data class UIState(
        val isLoading: Boolean = false,
        val items: ItemsModelsType? = null,
        val itemsTypeWeapons: ItemsModelsTypeWeapons? = null,
        val itemsTypeHouseHold: HouseHoldModel? = null,
        val plantsAnimalsProductsFoodDrink: PlantsAnimalsProductsFoodDrink? = null,
        val itemsTypeToolsAndOthers: ToolsAndOtherEquipmentModel? = null,
        val itemsTypeOtherItems: OtherItemsModel? = null,
        val error: String? = null
    )
}