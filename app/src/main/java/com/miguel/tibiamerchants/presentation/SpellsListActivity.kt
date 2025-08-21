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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miguel.tibiamerchants.presentation.Components.CardSpells
import com.miguel.tibiamerchants.presentation.Components.CardSpellsRunes
import com.miguel.tibiamerchants.presentation.Components.ErrorMessage
import com.miguel.tibiamerchants.presentation.Components.Loading
import com.miguel.tibiamerchants.presentation.Components.ToolBarSpells
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelSpells
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelSpellsFactory
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SpellsListActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelSpellsFactory by inject()
        val viewModel = ViewModelProvider(this, factory)[ViewModelSpells::class.java]
        viewModel.isBack.observe(this){
            if (it){
                finish()
            }
        }
        enableEdgeToEdge()
        setContent {
            TibiaMerchantsTheme {
                Scaffold(modifier = Modifier) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
                        ToolBarSpells("Spells", viewModel)
                        SwipeRefreshSpells(
                            viewModel = viewModel,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ListSpellsandRuneslist(
    modifier: Modifier = Modifier,
    spellsDataState: ViewModelSpells.UIState,
    viewModel: ViewModelSpells,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        //val tools = items.body
        val spells = spellsDataState.spells?.body?.spells
        val runes = spellsDataState.spells?.body?.runes
        spells?.let {
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
            items(it.size) { item ->
                CardSpells(
                    modifier = modifier.padding(5.dp),
                    item = it[item],
                    onClick = {}
                )
            }
        }
        runes?.let {
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
            items(it.size) { item ->
                CardSpellsRunes(
                    modifier = modifier.padding(5.dp),
                    item = it[item],
                    onClick = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeRefreshSpells(
    viewModel: ViewModelSpells,
    modifier: Modifier,
) {
    val spells = viewModel.spells.collectAsStateWithLifecycle()
    val corrutineScope = rememberCoroutineScope()
    val state = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        state = state,
        modifier = modifier,
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
                viewModel.setSpellsRefresh()
                delay(150)
                isRefreshing = false
            }
        }
    ) {
        when{
            spells.value.isLoading -> {
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
            spells.value.error != null->{
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    ErrorMessage(
                        messageHeader = "Error",
                        message = "An error occurred while loading data, please try again.",
                        modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                        onRetry = {
                          viewModel.spells()
                        }
                    )
                }
            }
            spells.value.spells != null->{
                ListSpellsandRuneslist(modifier, spells.value, viewModel)
            }

            else->{
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    ErrorMessage(
                        messageHeader = "Error",
                        message = "An error occurred while loading data, please try again.",
                        modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                        onRetry = {
                            viewModel.spells()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    TibiaMerchantsTheme {
        ToolBarSpells("Spells", null)
    }
}