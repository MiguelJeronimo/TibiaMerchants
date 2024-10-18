package com.miguel.tibiamerchants.data.repositories

import androidx.lifecycle.MutableLiveData
import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import com.miguel.tibiamerchants.data.network.retrofit.RetrofitClient
import com.miguel.tibiamerchants.domain.models.HouseHoldModel
import com.miguel.tibiamerchants.domain.models.ItemsModels
import com.miguel.tibiamerchants.domain.models.ItemsModelsType
import com.miguel.tibiamerchants.domain.models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.domain.models.OtherItemsModel
import com.miguel.tibiamerchants.domain.models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.ToolsAndOtherEquipmentModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryItems : Items {
    private val url = "https://tibia-merchants-api.onrender.com/"
    private val retrofit = RetrofitClient().getRetrofit(url)
    private val service = retrofit.create(ApiClient::class.java)

    override fun items(_items: MutableLiveData<ItemsModels>) {
        val call = service.items()
        call.enqueue(object : Callback<ItemsModels> {
            override fun onResponse(call: Call<ItemsModels>, response: Response<ItemsModels>) {
                if (response.isSuccessful) {
                    val items = response.body()
                    _items.value = items
                } else {
                    _items.value = null
                }
            }

            override fun onFailure(call: Call<ItemsModels>, t: Throwable) {
                _items.value = null
            }
        })
    }

    override fun itemsType(_items: MutableLiveData<ItemsModelsType>, body: PostItemsType) {
        val call = service.itemsType(body)
        call.enqueue(object : Callback<ItemsModelsType> {
            override fun onResponse(call: Call<ItemsModelsType>, response: Response<ItemsModelsType>) {
                if (response.isSuccessful){
                    val items = response.body()
                    _items.value = items
                } else{
                    _items.value = null
                }
            }

            override fun onFailure(call: Call<ItemsModelsType>, t: Throwable) {
                _items.value = null
            }

        })
    }

    override fun itemsTypeWeapons(_items: MutableLiveData<ItemsModelsTypeWeapons>, body: PostItemsType) {
        val call = service.itemsTypeWeapons(body)
        call.enqueue(object : Callback<ItemsModelsTypeWeapons> {
            override fun onResponse(
                call: Call<ItemsModelsTypeWeapons>,
                response: Response<ItemsModelsTypeWeapons>) {
                if(response.isSuccessful){
                    println(response.body())
                    val items = response.body()
                    _items.value = items
                } else{
                    _items.value = null
                }
            }

            override fun onFailure(call: Call<ItemsModelsTypeWeapons>, t: Throwable) {
                _items.value = null
            }
        })
    }

    override fun itemsTypeHouseHold(_items: MutableLiveData<HouseHoldModel>, body: PostItemsType) {
        println(body)
        val call = service.itemsTypeHouseHold(body)
        call.enqueue(object: Callback<HouseHoldModel>{
            override fun onResponse(call: Call<HouseHoldModel>, response: Response<HouseHoldModel>) {
                println("response ${response.body()}")
                if (response.isSuccessful){
                    val items = response.body()
                    _items.value = items
                } else{
                    _items.value = null
                }
            }

            override fun onFailure(call: Call<HouseHoldModel>, t: Throwable) {
                _items.value = null
            }
        })
    }

    override fun itemsTypeOthers(_items: MutableLiveData<PlantsAnimalsProductsFoodDrink>, body: PostItemsType) {
        println(body)
        val call = service.itemsTypeOthers(body)
        call.enqueue(object: Callback<PlantsAnimalsProductsFoodDrink>{
            override fun onResponse(
                call: Call<PlantsAnimalsProductsFoodDrink>,
                response: Response<PlantsAnimalsProductsFoodDrink>,
            ) {
                println("response ${response.body()}")
                if (response.isSuccessful){
                    val items = response.body()
                    _items.value = items
                } else{
                    _items.value = null
                }
            }

            override fun onFailure(call: Call<PlantsAnimalsProductsFoodDrink>, t: Throwable) {
                _items.value = null
            }
        })
    }

    override fun itemsTypeToolsAndOthers(
        _items: MutableLiveData<ToolsAndOtherEquipmentModel>,
        body: PostItemsType,
    ) {
        println(body)
        val call = service.itemsTypeToolsAndOthers(body)
        call.enqueue(object: Callback<ToolsAndOtherEquipmentModel>{
            override fun onResponse(
                call: Call<ToolsAndOtherEquipmentModel>,
                response: Response<ToolsAndOtherEquipmentModel>,
            ) {
                println("response ${response.body()}")
                if (response.isSuccessful){
                    val items = response.body()
                    _items.value = items
                } else{
                    _items.value = null
                }
            }

            override fun onFailure(call: Call<ToolsAndOtherEquipmentModel>, t: Throwable) {
                _items.value = null
            }
        })
    }

    override fun itemsTypeOtherItems(
        _items: MutableLiveData<OtherItemsModel>,
        body: PostItemsType,
    ) {
        val call = service.itemsTypeOtherItems(body)
        call.enqueue(object: Callback<OtherItemsModel>{
            override fun onResponse(call: Call<OtherItemsModel>, response: Response<OtherItemsModel>) {
                if (response.isSuccessful){
                    val items = response.body()
                    _items.value = items
                } else{
                    _items.value = null
                }
            }

            override fun onFailure(call: Call<OtherItemsModel>, t: Throwable) {
                _items.value = null
            }
        })
    }
}