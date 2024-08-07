package com.miguel.tibiamerchants.Repository

import androidx.lifecycle.MutableLiveData
import com.miguel.tibiamerchants.Models.ItemsModels
import com.miguel.tibiamerchants.Models.ItemsModelsType
import com.miguel.tibiamerchants.Models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.Models.PostItemsType

interface Items {
    /**
     * Return items list
     * **/
    fun items(_items: MutableLiveData<ItemsModels>)
    fun itemsType(_items: MutableLiveData<ItemsModelsType>, body: PostItemsType)
    fun itemsTypeWeapons(_items: MutableLiveData<ItemsModelsTypeWeapons>, body: PostItemsType)
}