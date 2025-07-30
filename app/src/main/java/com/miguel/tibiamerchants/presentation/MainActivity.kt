package com.miguel.tibiamerchants.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.domain.models.navigation.NavigationMain
import com.miguel.tibiamerchants.presentation.Components.Toobar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelNPCS
import com.miguel.tibiamerchants.presentation.fragments.NPCDefaultFragment
import com.miguel.tibiamerchants.presentation.fragments.TCPriceFragment
import com.miguel.tibiamerchants.presentation.fragments.TcPriceFragment
import com.miguel.tibiamerchants.presentation.fragments.TibiaTradeFragment
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import kotlinx.coroutines.launch


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

        viewModel.stateSpells.observe(this, Observer {
            if(it){
                Intent(this, SpellsListActivity::class.java).also{
                    startActivity(it)
                }
            }
        })

        viewModel.vocations.observe(this) {
            if (it){
                Intent(this, Vocations::class.java).also {
                    startActivity(it)
                }
            }
        }


        enableEdgeToEdge()
        setContent {
            TibiaMerchantsTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                //state for bottom navigation bar:
                val navController = rememberNavController()
                val startDestination = NavigationMain.NPCDefaultFragment
                var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.surface
                                    )
                            ) {
                                Image(
                                    painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                                    contentDescription = "Logo",
                                    modifier = Modifier.align(Alignment.CenterStart)
                                )
                                Spacer(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                                Column (Modifier.align(Alignment.Center)){
                                    Text(
                                        "Tibia Merchants",
                                        //modifier = ,
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                    Text(
                                        "Version: 1.0",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        "Made by Miguel",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                            HorizontalDivider()
                            NavigationDrawerItem(
                                label = { Text(text = "Spells") },
                                selected = false,
                                modifier = Modifier.padding(5.dp),
                                onClick = { viewModel.setSpellsState(true) }
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
                                onClick = { viewModel.setVocationsState(true) }
                            )
                        }
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        floatingActionButton = {
                            ExtendedFloatingActionButton(
                                text = { Text("Options") },
                                icon = { Icon(Icons.Filled.Menu, contentDescription = "") },
                                onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }
                            )
                        },
                        bottomBar = {
                            NavigationBar {
                                NavigationBar(
                                    windowInsets = NavigationBarDefaults.windowInsets ,
                                    modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                                ) {
                                    NavigationMain.entries.forEachIndexed { index, destination->
                                        NavigationBarItem(
                                            selected = selectedDestination == index,
                                            onClick = {
                                                navController.navigate(route = destination.route)
                                                selectedDestination = index
                                            },
                                            icon = {
                                                Icon(
                                                    modifier = Modifier.size(30.dp),
                                                    painter = painterResource(
                                                        id = destination.icon
                                                    ),
                                                    contentDescription = null
                                                )
                                            },
                                            label = { Text(destination.label) }
                                        )
                                    }
                                }
                            }
                        }
                    ) {innerPadding ->
                            val backStackEntry = navController.currentBackStackEntryAsState()
                            NavHost(
                                navController = navController,
                                startDestination = startDestination.name,
                                modifier = Modifier.padding(innerPadding)
                            ) {
                                NavigationMain.entries.forEach { destination->
                                    composable(destination.route){
                                        when(destination){
                                            NavigationMain.NPCDefaultFragment -> NPCDefaultFragment(viewModel = viewModel)
                                            NavigationMain.TCPrices -> TcPriceFragment(navController)
                                            NavigationMain.TibiaTrade -> TibiaTradeFragment(navController)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
          //  }


        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.setNPCName(null)
        viewModel.setItemsState(false)
        viewModel.setSpellsState(false)
        viewModel.setVocationsState(false)
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