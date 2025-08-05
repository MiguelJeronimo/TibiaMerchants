package com.miguel.tibiamerchants.presentation.fragments

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import model.Tibia.NPC

@Composable
fun NPCProfile(modifier: Modifier = Modifier, data: NPC){
    val stateChipBuyItems = rememberSaveable { mutableStateOf(false) }
    val stateChipSellItems = rememberSaveable { mutableStateOf(false) }
    val stateChipSellSpells = rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
    ) {
        Log.d("TAG", "NPCProfile: $data")
        if (data.nameNPC != null){
            ListItems(
                npc = data,
                stateChipBuyItems,
                stateChipSellItems,
                stateChipSellSpells
            )
        }
    }
}


@Composable
fun ChipFilter(text: String, state: MutableState<Boolean>){
    var selected by rememberSaveable { mutableStateOf(true) }
    state.value = selected
    FilterChip(
        modifier = Modifier.padding(5.dp, 0.dp, 5.dp, 0.dp),
        onClick = {
            selected = !selected
            state.value = selected
        },
        label = {
            Text(text)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardDescription(itemNPC: NPC?) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {


            //imagen del npc
            Box(modifier = Modifier.fillMaxWidth()){
                GlideImage(model = itemNPC?.imgNPC
                    , contentDescription = "rashid",
                    Modifier
                        .size(200.dp)
                        .align(Alignment.Center)
                        .padding(10.dp)
                )
            }
            //imagen de la ubicacion
            Box(modifier = Modifier.fillMaxWidth()){
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        text = "Localization",
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                    )

                }
            }
            Text(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp),
                style = MaterialTheme.typography.bodyLarge,
                text = itemNPC?.description.toString()
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedCard {
                Column(Modifier.padding(16.dp)) {
                    Text(text = "Nearest City: ${itemNPC?.citys!![0].name}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Gender: ${itemNPC?.gender}")
                    Text(text = "Race: ${itemNPC?.race}")
                    Text(text = "Job: ${itemNPC?.job}")
                    Text(text = "Version: ${itemNPC?.version}")
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Status: ${itemNPC?.status}", Modifier.align(Alignment.End), style = MaterialTheme.typography.bodyLarge, color = Color.Green)
        }
    }
}

@Composable
fun ListItems(
    npc: NPC,
    stateChipBuyItems: MutableState<Boolean>,
    stateChipSellItems: MutableState<Boolean>,
    stateChipSellSpells: MutableState<Boolean>,
) {
    LazyColumn {
        item {
            Row (
                Modifier
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                if (npc.buyingItems != null){
                    ChipFilter(
                        text = "Buy items",
                        state = stateChipBuyItems
                    )
                }
                if (npc.sellingItems != null){
                    ChipFilter(
                        text ="Sell items",
                        state = stateChipSellItems
                    )
                }
                if (npc.sellingSpells != null){
                    ChipFilter(
                        text = "Sell spells",
                        state = stateChipSellSpells
                    )
                }
            }
        }
        item {
            CardDescription(npc)
        }
        if (stateChipBuyItems.value) {
            item {
                Column {
                    Text(
                        text = "Buy Items",
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Divider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            items(npc.buyingItems!!.size) { item ->
                CardItems(
                    nameItem = npc.buyingItems!![item].name.toString(),
                    url = npc.buyingItems!![item].img.toString(),
                    price = npc.buyingItems!![item].price.toString(),
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp)
                )
            }
        }
        if (stateChipSellItems.value) {
            item {
                Column {
                    Text(
                        text = "Sell Items",
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Divider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            npc.sellingItems?.let{
                items(it.size) { item ->
                    CardItems(
                        nameItem = it[item].name.toString(),
                        url = it[item].img.toString(),
                        price = it[item].price.toString(),
                        Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp)
                    )
                }
            }
        }

        if (stateChipSellSpells.value) {
            item {
                Column {
                    Text(
                        text = "Sell Spells",
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Divider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            items(npc.sellingSpells!!.size) { item ->
                CardItems(
                    nameItem = npc.sellingSpells!![item].name.toString(),
                    url = npc.sellingSpells!![item].img.toString(),
                    price = npc.sellingSpells!![item].price.toString(),
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp)
                )
            }
        }
    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItems(
    nameItem: String,
    url: String,
    price: String,
    modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        onClick = {}
    ) {
        Row(Modifier.fillMaxWidth(1f)){
            GlideImage(model = url, contentDescription ="itemtibia",
                Modifier
                    .size(80.dp)
                    .padding(10.dp))
            Column(Modifier.padding(16.dp)) {
                Text(
                    //Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
                    text = nameItem,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = price,
                )
            }
        }
    }
}