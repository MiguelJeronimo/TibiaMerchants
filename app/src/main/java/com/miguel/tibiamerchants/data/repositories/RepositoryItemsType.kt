package com.miguel.tibiamerchants.data.repositories

import com.miguel.tibiamerchants.domain.models.HouseHoldModel
import com.miguel.tibiamerchants.domain.models.ItemsModelsType
import com.miguel.tibiamerchants.domain.models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.domain.models.OtherItemsModel
import com.miguel.tibiamerchants.domain.models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.ToolsAndOtherEquipmentModel

interface RepositoryItemsType {
    suspend fun itemsType(body: PostItemsType): ItemsModelsType?
    suspend fun itemsTypeWeapons( body: PostItemsType): ItemsModelsTypeWeapons?
    suspend fun itemsTypeHouseHold(body: PostItemsType): HouseHoldModel?
    suspend fun itemsTypeOthers(body: PostItemsType): PlantsAnimalsProductsFoodDrink?
    suspend fun itemsTypeToolsAndOthers(body: PostItemsType): ToolsAndOtherEquipmentModel?
    suspend fun itemsTypeOtherItems(body: PostItemsType): OtherItemsModel?
}