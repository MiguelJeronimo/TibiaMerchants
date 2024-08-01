package com.miguel.tibiamerchants.Repository

import androidx.lifecycle.MutableLiveData
import com.miguel.tibiamerchants.API.ApiTibia.ApiClient
import com.miguel.tibiamerchants.API.ApiTibia.retrofit.RetrofitClient
import com.miguel.tibiamerchants.Models.ItemsModels
import com.miguel.tibiamerchants.Models.ItemsModelsType
import com.miguel.tibiamerchants.Models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.Models.PostItemsType
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


}