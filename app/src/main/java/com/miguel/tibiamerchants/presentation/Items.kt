package com.miguel.tibiamerchants.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.models.ItemsModels
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.presentation.Components.SwipeRefresh
import com.miguel.tibiamerchants.presentation.Components.SwipeRefreshItemsType
import com.miguel.tibiamerchants.presentation.Components.Toolbar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelItems
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelItemsFactory
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import org.koin.android.ext.android.inject

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
class Items : ComponentActivity() {

    lateinit var viewModel: ViewModelItems
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelItemsFactory by inject()
        viewModel = ViewModelProvider(this, factory)[ViewModelItems::class.java]
        enableEdgeToEdge()
        setContent {
            val stateList = remember { mutableStateOf(ItemsModels()) }
            val stateProgressBar = remember { mutableStateOf(false) }
            //Refresh
            val pullToRefreshState = rememberPullToRefreshState()
            viewModel.post.observe(this, Observer { data->
                if (data.name != null){
                    Intent(this, Itemsype::class.java).also{
                        it.putExtra("name", data.name)
                        it.putExtra("title", data.title)
                        startActivity(it)
                    }
                }
            })
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
                               Toolbar("Items", viewmodel = viewModel)
                               SwipeRefresh( scaffoldNavigator = scaffoldNavigator, scope = scope)
                           }
                       },
                       detailPane = {
                           AnimatedPane {
                               scaffoldNavigator.currentDestination?.contentKey?.let {
                                   Log.d("Title", "${it.title}")
                                   Log.d("Name", "${it.name}")
                                   Column(modifier = Modifier) {
                                       //Toolbar(nameState.value.toString(), viewModel)
                                       val title = remember { mutableStateOf(it.title) }
                                       val name = remember { mutableStateOf(it.name) }
                                       SwipeRefreshItemsType(
                                           titleState = title,
                                           nameState = title,
                                           modifier = Modifier
                                       )
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