package com.miguel.tibiamerchants.data.repositories

import androidx.lifecycle.MutableLiveData
import com.miguel.tibiamerchants.domain.models.HouseHoldModel
import com.miguel.tibiamerchants.domain.models.ItemsModels
import com.miguel.tibiamerchants.domain.models.ItemsModelsType
import com.miguel.tibiamerchants.domain.models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.domain.models.OtherItemsModel
import com.miguel.tibiamerchants.domain.models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.ToolsAndOtherEquipmentModel

interface Items {
    /**
     * Return items list
     * **/
    fun items(_items: MutableLiveData<ItemsModels>)
    fun itemsType(_items: MutableLiveData<ItemsModelsType>, body: PostItemsType)
    fun itemsTypeWeapons(_items: MutableLiveData<ItemsModelsTypeWeapons>, body: PostItemsType)
    fun itemsTypeHouseHold(_items: MutableLiveData<HouseHoldModel>, body: PostItemsType)
    fun itemsTypeOthers(_items: MutableLiveData<PlantsAnimalsProductsFoodDrink>, body: PostItemsType)
    fun itemsTypeToolsAndOthers(_items: MutableLiveData<ToolsAndOtherEquipmentModel>, body: PostItemsType)
    fun itemsTypeOtherItems(_items: MutableLiveData<OtherItemsModel>, body: PostItemsType)

}