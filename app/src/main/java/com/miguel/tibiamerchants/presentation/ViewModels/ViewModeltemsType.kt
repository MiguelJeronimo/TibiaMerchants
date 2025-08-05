package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
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
import kotlinx.serialization.Serializable

class ViewModeltemsType(private val useCaseItemsType: UseCaseItemsType,private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _items = MutableStateFlow(UIState())
    val items: StateFlow<UIState> = _items

    companion object{
        private val LAST_QUERY_KEY = "last_query"
        private val DATA_KEY = "data"
    }

//    init {
//        savedStateHandle.get<PostItemsType>(LAST_QUERY_KEY)?.let {
//            _items.value = _items.value.copy(lastQuery = it)
//        }
//    }


    private val _name = MutableLiveData<String>()
    val name: MutableLiveData<String> = _name


    fun setItems(body: PostItemsType, force: Boolean = false) {
        viewModelScope.launch {
            val current = _items.value
            if (current.items != null  && !force && current.lastQuery == body) return@launch
            _items.value = _items.value.copy(isLoading = true, error = null)
            val response = useCaseItemsType.itemsType(body)
            response.onSuccess {
                _items.value = UIState(items = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = UIState(error = it.message, lastQuery = body)
            }
        }
    }

    fun setItemsWeapons(body: PostItemsType, force: Boolean = false) {
        viewModelScope.launch {
            val current = _items.value
            if (current.itemsTypeWeapons != null  && !force && current.lastQuery == body) return@launch
            _items.value = UIState(isLoading = true, error = null)
            val response = useCaseItemsType.itemsTypeWeapons(body)
            response.onSuccess {
                _items.value = UIState(itemsTypeWeapons = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = UIState(error = it.message, lastQuery = body)
            }
        }
    }

    fun setItemsHouseHold(body: PostItemsType, force: Boolean = false) {
        viewModelScope.launch {
            val current = _items.value
            if (current.itemsTypeHouseHold != null  && !force && current.lastQuery == body) return@launch
            _items.value  = UIState(isLoading = true, error = null)
            val response = useCaseItemsType.itemsTypeHouseHold(body)
            response.onSuccess {
                _items.value = UIState(itemsTypeHouseHold = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = UIState(error = it.message, lastQuery = body)
            }
        }
    }

    fun setPlantsAnimalsProductsFoodDrink(body: PostItemsType, force: Boolean = false){
        viewModelScope.launch {
            val current = _items.value
            if (current.plantsAnimalsProductsFoodDrink != null  && !force && current.lastQuery == body) return@launch
            _items.value  = UIState(isLoading = true, error = null)
            val response = useCaseItemsType.itemsTypeOthers(body)
            response.onSuccess {
                _items.value = UIState(plantsAnimalsProductsFoodDrink = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = UIState(error = it.message, lastQuery = body)
            }
        }
    }

    fun setItemsToolsAndOthers(body: PostItemsType, force: Boolean = false){
        viewModelScope.launch {
            val current = _items.value
            if (current.itemsTypeToolsAndOthers != null  && !force && current.lastQuery == body) return@launch
            _items.value = UIState(isLoading = true, error = null)
            val response = useCaseItemsType.itemsTypeToolsAndOthers(body)
            response.onSuccess {
                _items.value = UIState(itemsTypeToolsAndOthers = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = UIState(error = it.message, lastQuery = body)
            }
        }
    }

    fun setItemsOtherItems(body: PostItemsType, force: Boolean = false){
        viewModelScope.launch {
            val current = _items.value
            if (current.itemsTypeOtherItems != null  && !force && current.lastQuery == body) return@launch
            _items.value = UIState(isLoading = true, error = null)
            val response = useCaseItemsType.itemsTypeOtherItems(body)
            response.onSuccess {
                _items.value = UIState(itemsTypeOtherItems = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = UIState(error = it.message, lastQuery = body)
            }
        }
    }

    @Serializable
    data class UIState(
        val isLoading: Boolean = false,
        val items: ItemsModelsType? = null,
        val itemsTypeWeapons: ItemsModelsTypeWeapons? = null,
        val itemsTypeHouseHold: HouseHoldModel? = null,
        val plantsAnimalsProductsFoodDrink: PlantsAnimalsProductsFoodDrink? = null,
        val itemsTypeToolsAndOthers: ToolsAndOtherEquipmentModel? = null,
        val itemsTypeOtherItems: OtherItemsModel? = null,
        val error: String? = null,
        val lastQuery: PostItemsType? = null
    )
}