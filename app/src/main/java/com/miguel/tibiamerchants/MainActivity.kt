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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.miguel.tibiamerchants.Views.About
import com.miguel.tibiamerchants.Views.Components.Toobar
import com.miguel.tibiamerchants.Views.Items
import com.miguel.tibiamerchants.Views.NPCInformation
import com.miguel.tibiamerchants.Views.ViewModels.ViewModelNPCS
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import com.miguel.tibiamerchants.utils.utils
import kotlinx.coroutines.launch
import model.Tibia.NPCModel


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ViewModelNPCS
    private lateinit var viewModelProvider: ViewModelProvider
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelProvider = ViewModelProvider(this)
        viewModel = viewModelProvider[ViewModelNPCS::class.java]
        viewModel.npc.observe(this, Observer {npc->
            if (npc != null){
                Intent(this, NPCInformation::class.java).also{
                    it.putExtra("npc", npc)
                    startActivity(it)
                }
            }
        })
        viewModel.stateAbout.observe(this, Observer {
            if (it){
                Intent(this, About::class.java).also{
                    startActivity(it)
                }
            }
        })
        //ViewModels to menu drawer
        viewModel.stateItems.observe(this, Observer {
            if (it){
                Intent(this, Items::class.java).also{
                    startActivity(it)
                }
            }
        })
        enableEdgeToEdge()
        setContent {
            TibiaMerchantsTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Text("Tibiamerchants", modifier = Modifier.padding(16.dp))
                            HorizontalDivider()
                            NavigationDrawerItem(
                                label = { Text(text = "Catalog") },
                                selected = false,
                                modifier = Modifier.padding(5.dp),
                                onClick = { /*TODO*/ }
                            )
                            NavigationDrawerItem(
                                label = { Text(text = "Items") },
                                selected = false,
                                modifier = Modifier.padding(5.dp),
                                onClick = {
                                    viewModel.setItemsState(true)
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text(text = "Vocations") },
                                selected = false,
                                modifier = Modifier.padding(5.dp),
                                onClick = { /*TODO*/ }
                            )
                        }
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        floatingActionButton = {
                            ExtendedFloatingActionButton(
                                text = { Text("Show drawer") },
                                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                                onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }
                            )
                        }
                    ) {innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Toobar(stateAbout =viewModel)
                            val npcs = utils().listNPC()
                            GridLayoutNPC(npcs, viewModel)
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.setNPCName(null)
        viewModel.setItemsState(false)
    }
}



@Composable
fun GridLayoutNPC(npcs: List<NPCModel>, viewModel: ViewModelNPCS?) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
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
fun CardNPC(npc: NPCModel, viewModel: ViewModelNPCS) {
    Card(
        onClick = {
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
                failure = placeholder(R.drawable.error_image_icon),
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
                Toobar(stateAbout = null)
            }
        }
    }
}