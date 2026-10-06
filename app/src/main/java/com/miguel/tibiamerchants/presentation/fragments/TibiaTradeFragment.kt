package com.miguel.tibiamerchants.presentation.fragments

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.miguel.tibiamerchants.domain.models.Trade
import com.miguel.tibiamerchants.domain.models.TradeItem
import com.miguel.tibiamerchants.domain.models.navigation.NavigationTibiaTrade
import com.miguel.tibiamerchants.presentation.Components.ErrorMessage
import com.miguel.tibiamerchants.presentation.Components.ItemTradeList
import com.miguel.tibiamerchants.presentation.Components.Loading
import com.miguel.tibiamerchants.presentation.Components.Toobar
import com.miguel.tibiamerchants.presentation.Components.Toolbar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelTibiaTrade
import org.koin.androidx.compose.koinViewModel

@Composable
fun TibiaTradeFragment(
    viewModel: ViewModelTibiaTrade = koinViewModel(),
    onProfileNavigate: (username: String) -> Unit = {}
){
    val state = viewModel.items.collectAsLazyPagingItems()
    //val profile = viewModel.profile.collectAsState()
    val navigate = rememberNavController()
    Box(modifier = Modifier.fillMaxSize()){
        NavHost(
            navController = navigate,
            startDestination = NavigationTibiaTrade.TibiaTradeFragment.route
        ){
            NavigationTibiaTrade.entries.forEach { destination ->
                composable(
                    destination.route,
//                    arguments = listOf(
//                        navArgument("id") { type = NavType.IntType },
//                        navArgument("itemId") { type = NavType.IntType },
//                        navArgument("itemTier") { type = NavType.IntType },
//                        navArgument("currencyType") { type = NavType.IntType },
//                        navArgument("type") { type = NavType.IntType }
//                    )
                ){
                    when(destination) {
                        NavigationTibiaTrade.TibiaTradeFragment -> TibiaTradeFragment(
                            state = state, onRetry = { state.retry() },
                            onNavigateToUserProfile = {
                                navigate.navigate(NavigationTibiaTrade.routeWithName(it))
                            },
                            onNavigationItem = {
                                Log.d("DEBUG", "TibiaTradeFragment navigate: $it")
                                navigate.navigate(
                                    NavigationTibiaTrade.routeWithId(
                                        id = it.id,
                                        itemId = it.itemId,
                                        itemTier = it.itemTier,
                                        currencyType = it.currencyType,
                                        type = it.type
                                    )
                                )
                            }
                        )

                        NavigationTibiaTrade.TibiaTradeProfile -> {
                            it.arguments?.getString("userName").let { userName ->
                                Column {
                                    Toolbar(title = "User", onClick = {onProfileNavigate(userName?: "")})
                                    TibiaTradeProfile(
                                        toolBarTitle = userName!!,
                                        modifier = Modifier.fillMaxSize(),
                                        onNavigationItem = { item ->
                                            navigate.navigate(
                                                NavigationTibiaTrade.routeWithId(
                                                    id = item.id,
                                                    itemId = item.itemId,
                                                    itemTier = item.itemTier,
                                                    currencyType = item.currencyType,
                                                    type = item.type
                                                )
                                            )
                                        }
                                    )
                                }
                            }
                        }

                        NavigationTibiaTrade.TibiaTradeItem -> {
                            val data = TradeItem(
                                id = it.arguments?.getString("id")?.toInt() ?: 0,
                                itemId = it.arguments?.getString("itemId")?.toInt() ?: 5124,
                                itemTier = it.arguments?.getString("itemTier")?.toInt() ?: 0,
                                currencyType = it.arguments?.getString("currencyType")?.toInt() ?: 0,
                                type = it.arguments?.getString("type")?.toInt() ?: 0
                            )
                            Log.d("DEBUG", "TibiaTradeFragment: $data")
                                TibiaTradeItemDetails(
                                    data = data,
                                    modifier = Modifier.fillMaxWidth(),
                                    onBack = {
                                        navigate.popBackStack()
                                    }
                                )

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TibiaTradeFragment(
    state: LazyPagingItems<Trade>,
    onRetry: () -> Unit = {},
    onNavigateToUserProfile: (userName: String) -> Unit = {},
    onNavigationItem: (data: TradeItem) -> Unit = {},
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
                                .width(64.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(5.dp)
                        )
                        Text(
                            text = "Loading...",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(10.dp)
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
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(10.dp),
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
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                        onRetry = onRetry
                    )
                }
            }
            else-> {
                ItemTradeList(
                    state = state,
                    onNavigationItem = onNavigationItem,
                    onNavigateToUserProfile = onNavigateToUserProfile
                )
            }
        }
    }
}
