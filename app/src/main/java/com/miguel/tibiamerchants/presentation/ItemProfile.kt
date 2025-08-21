package com.miguel.tibiamerchants.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ItemProfile : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val pullToRefreshState by remember { mutableStateOf(false) }
            val progressState = remember { mutableStateOf(false) }
            val nameIntent = remember { mutableStateOf("") }
            var profileState by remember { mutableStateOf(Profile()) }
            val factory: ViewModelItemProfileFactory by inject()
            val viewModel = ViewModelProvider(this, factory)[ViewModelItemProfile::class.java]
            val name = intent.getStringExtra("name")
            if (name != null) {
                nameIntent.value = name
            }

            TibiaMerchantsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ToolBarItemsProfile(nameIntent.value, viewmodel = viewModel)
                        if (progressState.value) {
                            ProgressIndicatorItemProfile()
                        }
//                        SwipeRefreshItemProfile(
//                            profileState = profileState,
//                            name = nameIntent.value,
//                            viewModel = viewModel,
//                            pullToRefreshState = pullToRefreshState,
//                            modifier = Modifier.fillMaxSize()
//                        )
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