package com.miguel.tibiamerchants.domain.usecases

import com.miguel.tibiamerchants.data.repositories.RepositoryItemsType
import com.miguel.tibiamerchants.domain.models.HouseHoldModel
import com.miguel.tibiamerchants.domain.models.ItemsModelsType
import com.miguel.tibiamerchants.domain.models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.domain.models.OtherItemsModel
import com.miguel.tibiamerchants.domain.models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.ToolsAndOtherEquipmentModel

class UseCaseItemsType(private val repository: RepositoryItemsType) {
    suspend fun itemsType(body: PostItemsType): ItemsModelsType? {
        return repository.itemsType(body)
    }
    suspend fun itemsTypeWeapons( body: PostItemsType): ItemsModelsTypeWeapons?{
        return repository.itemsTypeWeapons(body)
    }
    suspend fun itemsTypeHouseHold(body: PostItemsType): HouseHoldModel?{
        return repository.itemsTypeHouseHold(body)
    }
    suspend fun itemsTypeOthers(body: PostItemsType): PlantsAnimalsProductsFoodDrink?{
        return repository.itemsTypeOthers(body)
    }
    suspend fun itemsTypeToolsAndOthers(body: PostItemsType): ToolsAndOtherEquipmentModel?{
        return repository.itemsTypeToolsAndOthers(body)
    }
    suspend fun itemsTypeOtherItems(body: PostItemsType): OtherItemsModel?{
        return repository.itemsTypeOtherItems(body)
    }
}