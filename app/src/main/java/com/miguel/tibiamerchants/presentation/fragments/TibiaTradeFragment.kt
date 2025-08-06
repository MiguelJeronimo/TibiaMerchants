package com.miguel.tibiamerchants.presentation.fragments

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.miguel.tibiamerchants.BuildConfig
import com.miguel.tibiamerchants.domain.models.Trade
import com.miguel.tibiamerchants.presentation.Components.ErrorMessage
import com.miguel.tibiamerchants.presentation.Components.ItemTradeList
import com.miguel.tibiamerchants.presentation.Components.Loading
import com.miguel.tibiamerchants.presentation.Components.Toobar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelTibiaTrade
import org.koin.androidx.compose.koinViewModel

@Composable
fun TibiaTradeFragment(
    navigate: NavController,
    viewModel: ViewModelTibiaTrade = koinViewModel()
){
    val state = viewModel.items.collectAsLazyPagingItems()
    TibiaTradeFragment(
        state = state,
        onRetry = {
            state.refresh()
        }
    )
}

@Composable
fun TibiaTradeFragment(
    state: LazyPagingItems<Trade>,
    onRetry: () -> Unit = {}
){
    Column {
        Toobar(title = "Tibia Trade")
        when {
            //init charge
            state.loadState.refresh is LoadState.Loading && state.itemCount == 0-> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ){
                        Loading(
                            modifier = Modifier
                                .width(64.dp).align(Alignment.CenterHorizontally).padding(5.dp)
                        )
                        Text(
                            text = "Loading...",
                            modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp)
                        )
                    }
                }
            }
            //is empty
            state.loadState.refresh is LoadState.NotLoading && state.itemCount == 0 ->{
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ){
                        Text(
                            text = "No have information",
                            modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            state.loadState.refresh is LoadState.Error -> {
                Box(modifier = Modifier.fillMaxSize()){
                    ErrorMessage(
                        messageHeader = "Ups!",
                        message = "Something went wrong trying to get the data",
                        modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                        onRetry = onRetry
                    )
                }
            }
            else-> {
                ItemTradeList(
                    state = state
                )
            }
        }
    }
}
