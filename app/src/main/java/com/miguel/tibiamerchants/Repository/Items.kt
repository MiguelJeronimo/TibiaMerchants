package com.miguel.tibiamerchants.Repository

import androidx.lifecycle.MutableLiveData
import com.miguel.tibiamerchants.Models.HouseHoldModel
import com.miguel.tibiamerchants.Models.ItemsModels
import com.miguel.tibiamerchants.Models.ItemsModelsType
import com.miguel.tibiamerchants.Models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.Models.OtherItemsModel
import com.miguel.tibiamerchants.Models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.Models.PostItemsType
import com.miguel.tibiamerchants.Models.ToolsAndOtherEquipmentModel

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