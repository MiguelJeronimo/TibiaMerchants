package com.miguel.tibiamerchants.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells
import com.miguel.tibiamerchants.presentation.Components.CardSpells
import com.miguel.tibiamerchants.presentation.Components.CardSpellsRunes
import com.miguel.tibiamerchants.presentation.Components.ToolBarSpells
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelSpells
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelSpellsFactory
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import kotlinx.coroutines.delay
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
            val pullToRefreshState = rememberPullToRefreshState()
            val spellsDataState = remember { mutableStateOf( ResponseSpells()) }
            val progressState = remember { mutableStateOf(false) }
            TibiaMerchantsTheme {
                viewModel.spells.observe(this) {
                    if (it != null) {
                        spellsDataState.value = it
                    } else {
                        Toast.makeText(this, "Error, Error conection", Toast.LENGTH_SHORT).show()
                    }
                    viewModel.isProgress(false)
                }

                viewModel.progress.observe(this){
                    if (it!= null){
                        progressState.value = it
                    }
                }

                Scaffold(modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
                        ToolBarSpells("Spells", viewModel)
                        if (progressState.value){
                            ProgressIndicator()
                        }
                        SwipeRefreshSpells(
                            stateList = spellsDataState,
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


@Composable
fun ListSpellsandRuneslist(
    modifier: Modifier = Modifier,
    spellsDataState: MutableState<ResponseSpells>,
    viewModel: ViewModelSpells,
) {
    LazyColumn(modifier = modifier) {
        //val tools = items.body
        val spells = spellsDataState.value.body?.spells
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
                CardSpells(
                    modifier = modifier, item = spells[item],
                    viewModel = viewModel
                )
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
                CardSpellsRunes(
                    modifier = modifier,
                    item = runes[item],
                    viewModel = viewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeRefreshSpells(
    stateList: MutableState<ResponseSpells>,
    viewModel: ViewModelSpells,
    pullToRefreshState: PullToRefreshState,
    modifier: Modifier,
) {
    val stateProgress = remember { mutableStateOf(false) }
    if (pullToRefreshState.isRefreshing) {
        viewModel.isProgress(true)
        LaunchedEffect(true) {
            viewModel.setSpells()
            delay(1500)
            viewModel.isProgress(false)
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
            ListSpellsandRuneslist(modifier, stateList, viewModel)
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
fun     ProgressIndicator() {
    LinearProgressIndicator(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 10.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    TibiaMerchantsTheme {
        ToolBarSpells("Spells", null)
    }
}