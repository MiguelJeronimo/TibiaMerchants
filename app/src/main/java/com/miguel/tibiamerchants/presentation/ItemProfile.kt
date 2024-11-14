package com.miguel.tibiamerchants.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells
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
            val factory: ViewModelItemProfileFactory by inject ()
            val viewModel = ViewModelProvider(this, factory)[ViewModelItemProfile::class.java]
            val name = intent.getStringExtra("name")
            if (name != null) {
                nameIntent.value = name
                println("NAMEEEEEEE: $name")
            }
            viewModel.setItemProfiel(nameIntent.value)
            viewModel.itemProfile.observe(this){
                println("DATAAA: ${it.body}")
            }
            TibiaMerchantsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
                        ToolBarItemsProfile("Spells")
                        if (progressState.value){
                            ProgressIndicator()
                        }
                        SwipeRefreshItemProfile(
                           // stateList = spellsDataState,
                            nameIntent.value,
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
    pullToRefreshState: PullToRefreshState?= null,
    modifier: Modifier
) {
    val stateProgress = remember { mutableStateOf(false) }
    if (pullToRefreshState!!.isRefreshing) {
        viewModel?.loading(true)
        LaunchedEffect(true) {
            //
            delay(1500)
            viewModel?.loading(false)
            pullToRefreshState.endRefresh()
        }
    }
    //while to SwipeRefresh is executing
    if (pullToRefreshState.progress>0.0){
        stateProgress.value = true
    }

    Box(
        Modifier
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
            .fillMaxSize()
    ) {
        if (!pullToRefreshState.isRefreshing) {
            //ListItemProfile(modifier!!, stateList)
        }
        if (stateProgress.value){
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullToRefreshState
            )
            stateProgress.value = false
        }
    }
}

@Composable
fun ListItemProfile(
    modifier: Modifier = Modifier,
    spellsDataState: MutableState<ResponseSpells>? = null,
) {
    LazyColumn(modifier = modifier) {
        //val tools = items.body
        val spells = spellsDataState!!.value.body?.spells
        val runes = spellsDataState.value.body?.runes
        if (spells != null) {
            item {
                Column {
                    Text(
                        text = "Spells",
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            items(spells.size) { item ->
//                CardSpells(
//                    modifier = modifier, item = spells[item]
//                )
            }
        }

        if (runes != null){
            item {
                Column {
                    Text(
                        text = "Runes",
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            items(runes.size) { item ->
//                CardSpellsRunes(
//                    modifier = modifier,
//                    item = runes[item]
//                )
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