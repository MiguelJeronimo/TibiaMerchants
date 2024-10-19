package com.miguel.tibiamerchants.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import org.koin.android.ext.android.inject

class SpellsListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelSpellsFactory by inject()
        val viewModel = ViewModelProvider(this, factory)[ViewModelSpells::class.java]
        enableEdgeToEdge()
        setContent {
            TibiaMerchantsTheme {
                val spellsDataState = remember { mutableStateOf( ResponseSpells()) }
                viewModel.spells.observe(this) {
                    println("Spells: $it")
                    if (it != null) {
                        spellsDataState.value = it
                    }
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
                        ToolBarSpells("Spells")
                        ListSpellsandRuneslist(Modifier.padding(innerPadding), spellsDataState)
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
                    modifier = modifier, item = spells[item]
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
                    modifier = modifier, item = runes[item]
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    TibiaMerchantsTheme {
        ToolBarSpells("Spells")
    }
}