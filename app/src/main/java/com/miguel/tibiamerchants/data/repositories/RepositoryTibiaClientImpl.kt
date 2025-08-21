package com.miguel.tibiamerchants.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.miguel.tibiamerchants.data.network.retrofit.ApiTibiaTradeClient
import com.miguel.tibiamerchants.data.repositories.pagingresources.PagingTibiaTradeResource
import com.miguel.tibiamerchants.domain.models.PriceTcModel
import com.miguel.tibiamerchants.domain.models.Profile
import com.miguel.tibiamerchants.domain.models.TibiaTradeItemModel
import com.miguel.tibiamerchants.domain.models.TibiaTradeModel
import com.miguel.tibiamerchants.domain.models.Trade
import kotlinx.coroutines.flow.Flow

class RepositoryTibiaClientImpl(private val api: ApiTibiaTradeClient): RepositoryTibiaClient {
    override suspend fun getTcPrices(): PriceTcModel? {
        return api.tcPrices().body()
    }

    override suspend fun getTrade(page: Int, sortType: Int): TibiaTradeModel? {
        return api.trade(page = page, sortType=sortType).body()
    }

    override suspend fun getTrade(params: Map<String, String>): TibiaTradeModel? {
        return api.trade(params).body()
    }

    override suspend fun getTradeById(id: Int): TibiaTradeItemModel? {
        return api.tradeById(id).body()
    }

    override fun getItemsType(pageSize: Int): Flow<PagingData<Trade>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                //enablePlaceholders = false,
                initialLoadSize = pageSize,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { PagingTibiaTradeResource(api) }
        ).flow
    }

    override suspend fun getUserProfile(user: String): Profile? {
        return api.userProfile(user).body()
    }
}