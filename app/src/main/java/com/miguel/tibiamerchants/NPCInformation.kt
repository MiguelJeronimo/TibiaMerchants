package com.miguel.tibiamerchants

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.miguel.tibiamerchants.Views.Components.Toobar
import com.miguel.tibiamerchants.Views.ViewModels.ViewModelNPC
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import model.Tibia.NPC

class NPCInformation : ComponentActivity() {
    lateinit var viewmodel: ViewModelNPC
    lateinit var viewModelProvider: ViewModelProvider
    @SuppressLint("CoroutineCreationDuringComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TibiaMerchantsTheme {
                val nameNPC = intent.extras?.getString("npc")
                var stateName by rememberSaveable { mutableStateOf("") }
                var npcInformationState by remember { mutableStateOf(NPC()) }
                stateName = nameNPC.toString()
                viewmodel = ViewModelProvider(this)[ViewModelNPC::class.java]
                viewmodel.setNpcInformation(stateName)
                viewmodel.npcInformation.observe(this, Observer {
                    if(it!= null){
                        npcInformationState = it
                        npcInformationState.buyingItems?.forEach { println(it) }
                    }
                })
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Toobar()
                        println("STate:"+npcInformationState)
                        Row (Modifier.padding(10.dp).align(Alignment.CenterHorizontally)){
                            ChipFilter()
                            ChipFilter()
                            ChipFilter()
                        }
                        if (npcInformationState.nameNPC != null){
                            ListItems(npc = npcInformationState)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChipFilter(){
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        modifier = Modifier.padding(5.dp, 0.dp, 5.dp, 0.dp),
        onClick = { selected = !selected },
        label = {
            Text("Filter chip")
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
                    GlideImage(model = itemNPC?.map
                        ,contentDescription = "rashid",
                        Modifier
                            .size(250.dp)
                            .padding(10.dp)
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
                    Text(text = "Nearest City: ${itemNPC?.nearestCity}", style = MaterialTheme.typography.bodyLarge)
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
fun ListItems(npc: NPC, modifier: Modifier = Modifier) {
    val npcList = npc.buyingItems
    println("List $npcList")
    LazyColumn {
        item {
            CardDescription(npc)
            Divider()
        }
        if (npc.buyingItems != null){
            items(npc.buyingItems!!.size) { item->
                CardItems(
                    nameItem = npc.buyingItems!![item].name.toString(),
                    url = npc.buyingItems!![item].img.toString(),
                    price = npc.buyingItems!![item].price.toString()
                    , Modifier.padding(16.dp, 5.dp, 16.dp,5.dp))
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TibiaMerchantsTheme {
        Column {
          CardItems(
              "algo",
                "algo",
              "9000"
          )
        }
    }
}