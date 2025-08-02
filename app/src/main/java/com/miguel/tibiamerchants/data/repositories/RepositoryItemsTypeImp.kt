package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import com.miguel.tibiamerchants.domain.models.HouseHoldModel
import com.miguel.tibiamerchants.domain.models.ItemsModelsType
import com.miguel.tibiamerchants.domain.models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.domain.models.OtherItemsModel
import com.miguel.tibiamerchants.domain.models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.ToolsAndOtherEquipmentModel

class RepositoryItemsTypeImp(private val retrofit: ApiClient): RepositoryItemsType {
    override suspend fun itemsType(body: PostItemsType): ItemsModelsType? {
        return retrofit.itemsType(body).body()
    }

    override suspend fun itemsTypeWeapons(body: PostItemsType): ItemsModelsTypeWeapons? {
        return retrofit.itemsTypeWeapons(body).body()
    }

    override suspend fun itemsTypeHouseHold(body: PostItemsType): HouseHoldModel? {
        return retrofit.itemsTypeHouseHold(body).body()
    }

    override suspend fun itemsTypeOthers(body: PostItemsType): PlantsAnimalsProductsFoodDrink? {
        return retrofit.itemsTypeOthers(body).body()
    }

    override suspend fun itemsTypeToolsAndOthers(body: PostItemsType): ToolsAndOtherEquipmentModel? {
        return retrofit.itemsTypeToolsAndOthers(body).body()
    }

    override suspend fun itemsTypeOtherItems(body: PostItemsType): OtherItemsModel? {
        return retrofit.itemsTypeOtherItems(body).body()
    }
}