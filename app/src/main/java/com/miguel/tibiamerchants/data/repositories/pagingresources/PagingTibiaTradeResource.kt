package com.miguel.tibiamerchants.data.repositories.pagingresources

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.miguel.tibiamerchants.data.network.retrofit.ApiTibiaTradeClient
import com.miguel.tibiamerchants.domain.models.Trade
import java.io.IOException
import kotlin.math.ceil

class PagingTibiaTradeResource(private val api: ApiTibiaTradeClient): PagingSource<Int, Trade>() {
    override fun getRefreshKey(state: PagingState<Int, Trade>): Int? {
        //return state.anchorPosition
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Trade> {
        return try {
            val page = params.key ?: 1
            val pagSize = params.loadSize
            val response = api.trade(page = page, sortType = 0)
            Log.d("PagingTibiaTradeResource", "Response: $response")
            val items = response.body()?.ads ?: emptyList()
            val count = response.body()?.count ?: 0
            val highlights = response.body()?.highlightedAds ?: emptyList()
            val combinedList = if (page == 1 && highlights.isNotEmpty()) highlights + items else items
            //total paginations.
            Log.d("PagingTibiaTradeResource", "PagSice: $pagSize")
            val totalPagination = ceil(count / params.loadSize.toDouble()).toInt()
            Log.d("PagingTibiaTradeResource", "PAginations: $totalPagination")
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (items.isNotEmpty() && items.size == 24 && page < totalPagination) page + 1 else null
            LoadResult.Page(data = combinedList, prevKey = prevKey, nextKey = nextKey)
        } catch (e: IOException){
            LoadResult.Error(e)
        }
    }
}