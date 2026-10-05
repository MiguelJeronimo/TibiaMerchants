package com.miguel.tibiamerchants.data.repositories

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.miguel.tibiamerchants.data.network.retrofit.ApiTibiaTradeClient
import com.miguel.tibiamerchants.data.network.retrofit.responses.PriceModel
import com.miguel.tibiamerchants.data.network.retrofit.responses.TibiaTradeItemProfileModel
import com.miguel.tibiamerchants.data.repositories.pagingresources.PagingTibiaTradeResource
import com.miguel.tibiamerchants.domain.models.Profile
import com.miguel.tibiamerchants.domain.models.TibiaTradeModel
import com.miguel.tibiamerchants.domain.models.Trade
import kotlinx.coroutines.flow.Flow

class RepositoryTibiaClientImpl(private val api: ApiTibiaTradeClient): RepositoryTibiaClient {
    override suspend fun getTcPrices(): List<PriceModel>? {
        return api.tcPrices().body()?.body
    }

    override suspend fun getTrade(page: Int, sortType: Int): TibiaTradeModel? {
        return api.trade(page = page, sortType=sortType).body()?.body
    }

    override suspend fun getTrade(params: Map<String, String>): TibiaTradeModel? {
        return api.trade(params).body()
    }

    override suspend fun getTradeById(
        id: Int,
        itemId: Int?,
        itemTier: Int?,
        currencyType: Int?,
        type: Int?
    ): TibiaTradeItemProfileModel? {
        Log.d("DEBUG", "Repository getTradeById: ${api.tradeById(
            id = id,
            itemId = itemId,
            itemTier = itemTier,
            currencyType = currencyType,
            type = type
        ).body()}")
        return api.tradeById(
            id = id,
            itemId = itemId,
            itemTier = itemTier,
            currencyType = currencyType,
            type = type
        ).body()?.body
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