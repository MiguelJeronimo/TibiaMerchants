package com.miguel.tibiamerchants.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.navigation.NavigationItemsDetails
import com.miguel.tibiamerchants.presentation.Components.SwapeRefreshItemsType
import com.miguel.tibiamerchants.presentation.Components.SwipeRefresh
import com.miguel.tibiamerchants.presentation.Components.SwipeRefreshItemProfile

import com.miguel.tibiamerchants.presentation.Components.Toolbar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelItems
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModeltemsType
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelItemsFactory
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import org.koin.android.ext.android.inject

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
class Items : ComponentActivity() {

    lateinit var viewModel: ViewModelItems
    lateinit var itemsModels: ViewModeltemsType
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelItemsFactory by inject()
        viewModel = ViewModelProvider(this, factory)[ViewModelItems::class.java]
        enableEdgeToEdge()
        setContent {
            viewModel.isBack.observe(this, Observer {
                if (it) {
                    finish()
                }
            })

            TibiaMerchantsTheme {
               val scaffoldNavigator  = rememberListDetailPaneScaffoldNavigator<PostItemsType>()
               val scope = rememberCoroutineScope()
               Scaffold {
                   NavigableListDetailPaneScaffold(
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(WindowInsets.systemBars.asPaddingValues()),
                       navigator = scaffoldNavigator,
                       listPane = {
                           Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                               Toolbar("Items", onClick = {
                                   viewModel.setBack(true)
                               })
                               SwipeRefresh( scaffoldNavigator = scaffoldNavigator, scope = scope)
                           }
                       },
                       detailPane = {
                           val scope = rememberCoroutineScope()
                           AnimatedPane {
                               val destination = scaffoldNavigator.currentDestination?.contentKey
                               val navController = rememberNavController()
                               if (destination != null) {
                                   NavHost(
                                       navController = navController,
                                       startDestination = NavigationItemsDetails.Items.route,
                                       modifier = Modifier.fillMaxSize()
                                   ) {
                                       NavigationItemsDetails.entries.forEach {dest->
                                           composable(dest.route){
                                               when(dest){
                                                   NavigationItemsDetails.Items -> {
                                                       var destinationState = rememberSaveable { PostItemsType() }
                                                       destinationState = destination
                                                       SwapeRefreshItemsType(
                                                           destination = destination,
                                                           destinationState = destinationState,
                                                           scope = scope,
                                                           scaffoldNavigator = scaffoldNavigator,
                                                           navController = navController,
                                                       )
                                                   }
                                                   NavigationItemsDetails.ItemDetails -> {
                                                       println("Data: ${it.arguments?.getString("itemName")}")
                                                       val itemName =
                                                           it.arguments?.getString("itemName")
                                                       itemName?.let {
                                                           SwipeRefreshItemProfile(
                                                               name = it,
                                                               navController = navController
                                                           )
                                                       }
                                                   }
                                               }
                                           }

                                       }
                                   }
                               }
                           }
                       },
                       extraPane = {
                           Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                               Toolbar("Items", viewmodel = viewModel)
                               SwipeRefresh(scaffoldNavigator = scaffoldNavigator, scope = scope)
                           }
                       }
                   )
               }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    TibiaMerchantsTheme {
    }
}