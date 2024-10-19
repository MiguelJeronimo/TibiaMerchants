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
        return try {
            val response = retrofit.itemsType(body)
            val resp = when(response.code()){
                200-> response.body()
                else -> null
            }
            resp
        }catch (e: Exception){
            println("Error Retrofit itemsType(): "+e.message)
            null
        }
    }

    override suspend fun itemsTypeWeapons(body: PostItemsType): ItemsModelsTypeWeapons? {
        return try {
            val response = retrofit.itemsTypeWeapons(body)
            val resp = when(response.code()){
                200-> response.body()
                else -> null
            }
            resp
        }catch (e:Exception){
            println("Error Retrofit itemsTypeWeapons(): "+e.message)
            null
        }
    }

    override suspend fun itemsTypeHouseHold(body: PostItemsType): HouseHoldModel? {
        return try{
            val response = retrofit.itemsTypeHouseHold(body)
            val resp = when(response.code()){
                200-> response.body()
                else -> null
            }
            resp
        } catch (e: Exception){
            println("Error Retrofit itemsTypeHouseHold(): "+e.message)
            null
        }
    }

    override suspend fun itemsTypeOthers(body: PostItemsType): PlantsAnimalsProductsFoodDrink? {
        return try {
            val response = retrofit.itemsTypeOthers(body)
            val resp = when(response.code()){
                200-> response.body()
                else -> null
            }
            resp
        } catch (e:Exception){
            println("Error Retrofit itemsTypeOthers(): "+e.message)
            null
        }
    }

    override suspend fun itemsTypeToolsAndOthers(body: PostItemsType): ToolsAndOtherEquipmentModel? {
        return try {
            val response = retrofit.itemsTypeToolsAndOthers(body)
            val resp = when(response.code()){
                200-> response.body()
                else -> null
            }
            resp
        } catch (e:Exception){
            println("Error Retrofit itemsTypeToolsAndOthers(): "+e.message)
            null
        }
    }

    override suspend fun itemsTypeOtherItems(body: PostItemsType): OtherItemsModel? {
        return try {
            val response = retrofit.itemsTypeOtherItems(body)
            val resp = when(response.code()){
                200-> response.body()
                else -> null
            }
            resp
        } catch (e:Exception){
            println("Error Retrofit itemsTypeOtherItems(): "+e.message)
            null
        }
    }
}