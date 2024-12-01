package com.miguel.tibiamerchants.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibia_merchants_api.domain.models.Profile
import com.miguel.tibiamerchants.presentation.Components.CardBuyFrom
import com.miguel.tibiamerchants.presentation.Components.CardDetails
import com.miguel.tibiamerchants.presentation.Components.CardHeaderItemInfo
import com.miguel.tibiamerchants.presentation.Components.CardMagicPropierties
import com.miguel.tibiamerchants.presentation.Components.CardOtherPropierties
import com.miguel.tibiamerchants.presentation.Components.CardRequeriments
import com.miguel.tibiamerchants.presentation.Components.CardSellFrom
import com.miguel.tibiamerchants.presentation.Components.CardTibiaLegends
import com.miguel.tibiamerchants.presentation.Components.ToolBarItemsProfile
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelItemProfile
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelItemProfileFactory
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject

class ItemProfile : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val pullToRefreshState = rememberPullToRefreshState()
            val progressState = remember { mutableStateOf(false) }
            val nameIntent = remember { mutableStateOf("") }
            var profileState by remember { mutableStateOf(Profile()) }
            val stateChipBuyFrom = remember { mutableStateOf(false) }
            val stateChipSellTo = remember { mutableStateOf(false) }
            val factory: ViewModelItemProfileFactory by inject()
            val viewModel = ViewModelProvider(this, factory)[ViewModelItemProfile::class.java]
            val name = intent.getStringExtra("name")
            if (name != null) {
                nameIntent.value = name
            }
            viewModel.setItemProfiel(nameIntent.value)
            viewModel.itemProfile.observe(this) {
                if (it != null) {
                    profileState = it.body!!
                } else {
                    Toast.makeText(this, "Error, profile not found", Toast.LENGTH_SHORT).show()
                }
                viewModel.loading(false)
            }
            viewModel.isLoading.observe(this){
                progressState.value = it
            }

            TibiaMerchantsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ToolBarItemsProfile(nameIntent.value)
                        if (progressState.value) {
                            ProgressIndicatorItemProfile()
                        }
                        SwipeRefreshItemProfile(
                            profileState = profileState,
                            stateChipBuyFrom = stateChipBuyFrom,
                            stateChipSellTo = stateChipSellTo,
                            name = nameIntent.value,
                            viewModel = viewModel,
                            pullToRefreshState = pullToRefreshState,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeRefreshItemProfile(
    name: String? = null,
    viewModel: ViewModelItemProfile? = null,
    pullToRefreshState: PullToRefreshState? = null,
    modifier: Modifier,
    profileState: Profile,
    stateChipBuyFrom: MutableState<Boolean>,
    stateChipSellTo: MutableState<Boolean>,
) {
    val stateProgress = remember { mutableStateOf(false) }
    if (pullToRefreshState!!.isRefreshing) {
        viewModel?.loading(true)
        LaunchedEffect(true) {
            viewModel?.setItemProfiel(name!!)
            delay(1500)
            viewModel?.loading(false)
            pullToRefreshState.endRefresh()
        }
    }
    //while to SwipeRefresh is executing
    if (pullToRefreshState.progress > 0.0) {
        stateProgress.value = true
    }

    Box(
        Modifier
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
            .fillMaxSize()
    ) {
        if (!pullToRefreshState.isRefreshing) {
            ProfileComposable(
                modifier = modifier,
                profileState = profileState,
                stateChipBuyFrom = stateChipBuyFrom,
                stateChipSellTo = stateChipSellTo
            )
        }
        if (stateProgress.value) {
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullToRefreshState,
            )
            stateProgress.value = false
        }
    }
}

@Composable
fun ProfileComposable(
    modifier: Modifier = Modifier,
    profileState: Profile,
    stateChipBuyFrom: MutableState<Boolean>,
    stateChipSellTo: MutableState<Boolean>,
) {
    LazyColumn(modifier = modifier) {
        //val tools = items.body
        if (profileState.name != null){
            item {
                Column {
                    CardHeaderItemInfo(profile = profileState)
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                    //CardNotes(profile = profileState)
                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        com.miguel.tibiamerchants.presentation.Components.ChipFilter("Buy for", state = stateChipBuyFrom)
                        com.miguel.tibiamerchants.presentation.Components.ChipFilter("Sell to", state = stateChipSellTo)
                    }
                    CardDetails(profile = profileState)
                    profileState.requeriments?.let {
                        CardRequeriments(profileState.requeriments)
                    }
                    profileState.otherPropierties?.let {
                        CardOtherPropierties(profileState.otherPropierties)
                    }

                    profileState.magicProperties?.let { CardMagicPropierties(profileState.magicProperties)  }
                    profileState.tibiaLengend?.let {
                        CardTibiaLegends(it)
                    }
                }
            }
        }

        if (stateChipBuyFrom.value){
            profileState.buyFrom?.let {
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
                if (buyFrom != null) {
                    items(buyFrom.size) { buy ->
                        CardBuyFrom(buyFrom = profileState.buyFrom!![buy])
                    }
                }
            }
        }

        if (stateChipSellTo.value){
            profileState.sellFrom?.let {
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
                if (sellFrom != null) {
                    items(sellFrom.size) { buy ->
                        CardSellFrom(sellFrom = profileState.sellFrom!![buy])
                    }
                }
            }
        }
    }
}


@Composable
fun ProgressIndicatorItemProfile() {
    LinearProgressIndicator(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 10.dp)
    )
}


@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    TibiaMerchantsTheme {
        Greeting2("Android")
    }
}