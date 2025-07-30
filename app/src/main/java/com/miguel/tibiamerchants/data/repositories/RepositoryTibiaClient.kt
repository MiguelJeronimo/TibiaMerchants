package com.miguel.tibiamerchants.data.repositories

import androidx.paging.PagingData
import com.miguel.tibiamerchants.domain.models.PriceTcModel
import com.miguel.tibiamerchants.domain.models.TibiaTradeModel
import com.miguel.tibiamerchants.domain.models.Trade
import kotlinx.coroutines.flow.Flow

interface RepositoryTibiaClient {
    suspend fun getTcPrices(): PriceTcModel?
    suspend fun getTrade(page: Int, sortType: Int): TibiaTradeModel?
    suspend fun getTrade(params: Map<String, String>): TibiaTradeModel?
    fun getItemsType(pageSize: Int = 24): Flow<PagingData<Trade>>
}