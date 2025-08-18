package com.miguel.tibiamerchants.data.repositories

import androidx.paging.PagingData
import com.miguel.tibiamerchants.domain.models.PriceTcModel
import com.miguel.tibiamerchants.domain.models.Profile
import com.miguel.tibiamerchants.domain.models.TibiaTradeItemModel
import com.miguel.tibiamerchants.domain.models.TibiaTradeModel
import com.miguel.tibiamerchants.domain.models.Trade
import kotlinx.coroutines.flow.Flow

interface RepositoryTibiaClient {
    suspend fun getTcPrices(): PriceTcModel?
    suspend fun getTrade(page: Int, sortType: Int): TibiaTradeModel?
    suspend fun getTrade(params: Map<String, String>): TibiaTradeModel?
    suspend fun getTradeById(id: Int): TibiaTradeItemModel?
    fun getItemsType(pageSize: Int = 24): Flow<PagingData<Trade>>
    suspend fun getUserProfile(user: String): Profile?
}