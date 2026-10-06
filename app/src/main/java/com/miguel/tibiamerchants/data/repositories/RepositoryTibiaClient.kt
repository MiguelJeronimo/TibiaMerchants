package com.miguel.tibiamerchants.data.repositories

import androidx.paging.PagingData
import com.miguel.tibiamerchants.data.network.retrofit.responses.PriceModel
import com.miguel.tibiamerchants.data.network.retrofit.responses.TibiaTradeItemProfileModel
import com.miguel.tibiamerchants.data.network.retrofit.responses.TibiaTradeUserProfileModel
import com.miguel.tibiamerchants.domain.models.Profile
import com.miguel.tibiamerchants.domain.models.TibiaTradeModel
import com.miguel.tibiamerchants.domain.models.Trade
import kotlinx.coroutines.flow.Flow

interface RepositoryTibiaClient {
    suspend fun getTcPrices(): List<PriceModel>?
    suspend fun getTrade(page: Int, sortType: Int): TibiaTradeModel?
    suspend fun getTrade(params: Map<String, String>): TibiaTradeModel?
    suspend fun getTradeById(id: Int, itemId: Int? = null, itemTier : Int? = null, currencyType: Int? = null, type: Int? = null): TibiaTradeItemProfileModel?
    fun getItemsType(pageSize: Int = 24): Flow<PagingData<Trade>>
    suspend fun getUserProfile(user: String): TibiaTradeUserProfileModel?
}