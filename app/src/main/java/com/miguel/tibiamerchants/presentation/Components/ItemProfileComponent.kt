package com.miguel.tibiamerchants.presentation.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.miguel.tibia_merchants_api.domain.models.Profile
import com.miguel.tibiamerchants.domain.models.navigation.NavigationItemsDetails
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelItemProfile
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SwipeRefreshItemProfile(
    viewmodel: ViewModelItemProfile = koinViewModel(),
    name: String,
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    viewmodel.setItemProfiel(name)
    val profileState = viewmodel.itemProfile.collectAsState()
    Column {
        Toolbar(
            title = name,
            onClick = {
                navController.popBackStack(
                    route = NavigationItemsDetails.Items.route,
                    inclusive = false
                )
            }
        )
        SwipeRefreshItemProfile(
            name = name,
            modifier = modifier,
            profileState = profileState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeRefreshItemProfile(
    name: String? = null,
    modifier: Modifier,
    profileState: State<ViewModelItemProfile.UIState>
) {
    val corrutineScope = rememberCoroutineScope()
    val state = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                state = state
            )
        },
        onRefresh = {
            isRefreshing = true
            corrutineScope.launch {
                delay(1500)
                isRefreshing = false
            }
        },
        modifier = modifier,
        state = state,
    ) {
        when{
            profileState.value._isLoading->{
                Box (
                    modifier = Modifier.fillMaxSize()
                ){
                    Column (
                        modifier = Modifier.align(Alignment.Center)
                    ){
                        Loading(modifier = modifier)
                        Text(
                            text = "Loading...",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
            profileState.value.itemProfile != null->{
                ProfileComposable(
                    modifier = modifier,
                    profileState = profileState.value.itemProfile?.body
                )
            }
            profileState.value.error != null->{
                ErrorComponent(
                   message = "${profileState.value.error}"
                )
            }
        }
    }
}

@Composable
fun ProfileComposable(
    modifier: Modifier = Modifier,
    profileState: Profile?
) {
    val stateChipBuyFrom = rememberSaveable { mutableStateOf(false) }
    val stateChipSellTo = rememberSaveable { mutableStateOf(false) }
    LazyColumn(modifier = modifier) {
        //val tools = items.body
        if (profileState?.name != null) {
            item {
                Column {
                    CardHeaderItemInfo(profile = profileState)
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                    //CardNotes(profile = profileState)
                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        if(profileState.buyFrom != null) {
                            com.miguel.tibiamerchants.presentation.Components.ChipFilter(
                                "Buy for",
                                state = stateChipBuyFrom
                            )
                        }

                        if (profileState.sellFrom != null) {
                            com.miguel.tibiamerchants.presentation.Components.ChipFilter(
                                "Sell to",
                                state = stateChipSellTo
                            )
                        }
                    }
                    CardDetails(profile = profileState)
                    profileState.requeriments?.let {
                        CardRequeriments(profileState.requeriments)
                    }
                    profileState.otherPropierties?.let {
                        CardOtherPropierties(profileState.otherPropierties)
                    }

                    profileState.magicProperties?.let { CardMagicPropierties(profileState.magicProperties) }
                    profileState.tibiaLengend?.let {
                        CardTibiaLegends(it)
                    }
                }
            }

            if (stateChipBuyFrom.value) {
                item {
                    Column {
                        HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                        Text(
                            text = "Buy from",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            style = com.miguel.tibiamerchants.ui.theme.Typography.titleLarge
                        )
                    }
                }
                val buyFrom = profileState.buyFrom
                items(buyFrom!!.size) { buy ->
                    CardBuyFrom(buyFrom = profileState.buyFrom!![buy])
                }
            }

            if (stateChipSellTo.value) {
                item {
                    Column {
                        HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                        Text(
                            text = "Sell to",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            style = com.miguel.tibiamerchants.ui.theme.Typography.titleLarge
                        )
                    }
                }
                val sellFrom = profileState.sellFrom
                items(sellFrom!!.size) { buy ->
                    CardSellFrom(sellFrom = profileState.sellFrom!![buy])
                }
            }
        }
    }
}