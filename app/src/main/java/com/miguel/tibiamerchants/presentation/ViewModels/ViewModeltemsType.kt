package com.miguel.tibiamerchants.presentation.ViewModels

import android.util.Log
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
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class ViewModeltemsType(private val useCaseItemsType: UseCaseItemsType,private val savedStateHandle: SavedStateHandle) : ViewModel() {
    //private val repository = RepositoryItems()

    private val _items = MutableStateFlow(UIState())
    val items: StateFlow<UIState> = _items

//    companion object{
//        private val LAST_QUERY_KEY = "last_query"
//    }
//
//    init {
//        savedStateHandle.get<PostItemsType>(LAST_QUERY_KEY)?.let {
//            setItems(it)
//        }
//    }

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

    fun setItems(body: PostItemsType, force: Boolean = false) {
        viewModelScope.launch {
            val current = _items.value
            if (current.items != null  && !force && current.lastQuery == body) return@launch
            _items.value = _items.value.copy(isLoading = true, error = null)
            val response = useCaseItemsType.itemsType(body)
            response.onSuccess {
                _items.value = _items.value.copy(isLoading = false, items = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = _items.value.copy(isLoading = false, error = it.message, lastQuery = body)
            }
        }
    }

    fun setItemsWeapons(body: PostItemsType, force: Boolean = false) {
        viewModelScope.launch {
            val current = _items.value
            if (current.items != null  && !force && current.lastQuery == body) return@launch
            _items.value = _items.value.copy(isLoading = true, error = null, lastQuery = body)
            val response = useCaseItemsType.itemsTypeWeapons(body)
            response.onSuccess {
                _items.value = _items.value.copy(isLoading = false, itemsTypeWeapons = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = _items.value.copy(isLoading = false, error = it.message, lastQuery = body)
            }
        }
    }
    fun setItemsHouseHold(body: PostItemsType, force: Boolean = false) {
        viewModelScope.launch {
            val current = _items.value
            if (current.items != null  && !force && current.lastQuery == body) return@launch
            _items.value = _items.value.copy(isLoading = true, error = null)
            val response = useCaseItemsType.itemsTypeHouseHold(body)
            response.onSuccess {
                _items.value = _items.value.copy(isLoading = false, itemsTypeHouseHold = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = _items.value.copy(isLoading = false, error = it.message, lastQuery = body)
            }
        }
    }

    fun setPlantsAnimalsProductsFoodDrink(body: PostItemsType, force: Boolean = false){
        viewModelScope.launch {
            val current = _items.value
            if (current.items != null  && !force && current.lastQuery == body) return@launch
            _items.value = _items.value.copy(isLoading = true, error = null)
            val response = useCaseItemsType.itemsTypeOthers(body)
            response.onSuccess {
                _items.value = _items.value.copy(isLoading = false, plantsAnimalsProductsFoodDrink = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = _items.value.copy(isLoading = false, error = it.message, lastQuery = body)
            }
        }
    }

    fun setItemsToolsAndOthers(body: PostItemsType, force: Boolean = false){
        viewModelScope.launch {
            val current = _items.value
            if (current.items != null  && !force && current.lastQuery == body) return@launch
            _items.value = _items.value.copy(isLoading = true, error = null)
            val response = useCaseItemsType.itemsTypeToolsAndOthers(body)
            response.onSuccess {
                _items.value = _items.value.copy(isLoading = false, itemsTypeToolsAndOthers = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = _items.value.copy(isLoading = false, error = it.message, lastQuery = body)
            }
        }
    }

    fun setItemsOtherItems(body: PostItemsType, force: Boolean = false){
        viewModelScope.launch {
            val current = _items.value
            if (current.items != null  && !force && current.lastQuery == body) return@launch
            _items.value = _items.value.copy(isLoading = true, error = null)
            val response = useCaseItemsType.itemsTypeOtherItems(body)
            response.onSuccess {
                _items.value = _items.value.copy(isLoading = false, itemsTypeOtherItems = it, lastQuery = body)
            }
            response.onFailure {
                _items.value = _items.value.copy(isLoading = false, error = it.message, lastQuery = body)
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