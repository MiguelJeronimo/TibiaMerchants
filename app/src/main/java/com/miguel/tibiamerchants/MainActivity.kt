package com.miguel.tibiamerchants

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.miguel.tibiamerchants.Views.Components.Toobar
import com.miguel.tibiamerchants.Views.ViewModels.ViewModelNPC
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import com.miguel.tibiamerchants.utils.utils
import model.Tibia.NPCModel


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ViewModelNPC
    private lateinit var viewModelProvider: ViewModelProvider

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelProvider = ViewModelProvider(this)
        viewModel = viewModelProvider.get(ViewModelNPC::class.java)
        viewModel.npc.observe(this, Observer {npc->
            Intent(this, NPCInformation::class.java).also{
                it.putExtra("npc", npc)
                startActivity(it)
            }
        })
        enableEdgeToEdge()
        setContent {
            TibiaMerchantsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Toobar()
                        val npcs = utils().listNPC()
                        GridLayoutNPC(npcs, viewModel)
                    }
                }
            }
        }
    }
}



@Composable
fun GridLayoutNPC(npcs: List<NPCModel>, viewModel: ViewModelNPC?) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
       items(npcs.size){npc->
           CardNPC(npcs[npc], viewModel!!)
       }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardNPC(npc: NPCModel, viewModel: ViewModelNPC) {
    Card(
        onClick = {
            println("click ${npc.imgNPC}")
            viewModel.setNPCName(npc.nameNPC.toString())

                  },
        Modifier
            //.size(width = 80.dp, height = 80.dp)
            .padding(5.dp)
    )
    {
        Box(
            Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            GlideImage(
                model = npc.imgNPC,
                modifier = Modifier
                    .size(width = 100.dp, height = 100.dp)
                    .align(Alignment.Center)
                    .padding(10.dp),
                contentDescription = "gif"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TibiaMerchantsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Toobar()
            }
        }
    }
}