package com.miguel.tibiamerchants.presentation.Components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModeltemsType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SwapeRefreshItemsType(
    viewModel: ViewModeltemsType = koinViewModel(),
    destination: PostItemsType,
    scope: CoroutineScope = rememberCoroutineScope(),
    scaffoldNavigator: ThreePaneScaffoldNavigator<PostItemsType>,
    navController: NavHostController
){
    if (destination != null) {
        val itemState = viewModel.items.collectAsState()
        Log.d("Title", "${destination.title}")
        Log.d("Name", "${destination.name}")
        LaunchedEffect(destination.title, destination.name) {
            Log.d("Title", "Se ejecuta el launch effect")
            when (destination.title!!.lowercase()) {
                "body equipment" -> {
                    println("Items body equipment")
                    viewModel.setItems(destination)}
                "weapons" -> {
                    println("Items weapons")
                    viewModel.setItemsWeapons(destination)
                }
                "household items" ->{
                    println("Items household items")
                    viewModel.setItemsHouseHold(destination)}
                "plants, animal products, food and drink" -> {
                    println("Items plants, animal products, food and drink")
                    viewModel.setPlantsAnimalsProductsFoodDrink(destination)}
                "tools and other equipment" -> {
                    println("Items tools and other equipment")
                    viewModel.setItemsToolsAndOthers(destination)}
                "other items" -> {
                    println("Items other items")
                    viewModel.setItemsOtherItems(destination)}
                else -> {
                    println(" ELSE Items body equipment")
                    viewModel.setItems(destination)
                }
            }
        }
        Column(modifier = Modifier) {
            Toolbar(title = destination.name?:"", onClick = {
                scope.launch {
                    scaffoldNavigator.navigateBack()
                }
            })
            SwipeRefreshItemsType(
                itemTypeState = itemState,
                titleState = destination.title?:"",
                nameState = destination.name?:"",
                modifier = Modifier,
                navController = navController
            )
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeRefreshItemsType(
    viewModel: ViewModeltemsType = koinViewModel(),
    modifier: Modifier,
    nameState: String,
    titleState: String,
    itemTypeState: State<ViewModeltemsType.UIState>,
    navController: NavHostController,
) {
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
            corrutineScope.launch {
                isRefreshing = true
                Log.d("name", nameState)
                Log.d("title", titleState)
                when (titleState.lowercase()) {
                    "body equipment" -> viewModel.setItems(
                        PostItemsType(
                            titleState,
                            nameState
                        )
                    )

                    "weapons" -> viewModel.setItemsWeapons(
                        PostItemsType(
                            titleState,
                            nameState
                        )
                    )

                    "household items" -> viewModel.setItemsHouseHold(
                        PostItemsType(
                            titleState,
                            nameState
                        )
                    )

                    "plants, animal products, food and drink" -> viewModel.setPlantsAnimalsProductsFoodDrink(
                        PostItemsType(titleState, nameState)
                    )

                    "tools and other equipment" -> viewModel.setItemsToolsAndOthers(
                        PostItemsType(titleState, nameState)
                    )

                    "other items" -> viewModel.setItemsOtherItems(
                        PostItemsType(
                            titleState,
                            nameState
                        )
                    )

                    else -> {
                        viewModel.setItems(PostItemsType(titleState, nameState))
                    }
                }
                delay(1500)
                isRefreshing = false
            }
        }
    ) {
        when{
            itemTypeState.value.isLoading->{
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
            itemTypeState.value.items?.body!=null->{
                Log.d("ITEMS", itemTypeState.value.items?.body.toString())
                ListItems(
                    modifier = modifier.padding(5.dp),
                    body = itemTypeState.value.items?.body,
                    navController = navController
                )
            }
            itemTypeState.value.itemsTypeWeapons != null->{
                ListItems(
                    modifier = modifier.padding(5.dp),
                    items = itemTypeState.value.itemsTypeWeapons!!,
                    navController = navController
                )
            }
            itemTypeState.value.itemsTypeHouseHold != null->{
                ListItems(
                    modifier = modifier.padding(5.dp),
                    items = itemTypeState.value.itemsTypeHouseHold!!,
                    viewModel = viewModel,
                    navController = navController
                )
            }
            itemTypeState.value.plantsAnimalsProductsFoodDrink != null->{
                ListItems(
                    modifier = modifier.padding(5.dp),
                    items = itemTypeState.value.plantsAnimalsProductsFoodDrink!!,
                    viewModel = viewModel,
                    navController = navController
                )
            }
            itemTypeState.value.itemsTypeToolsAndOthers != null->{
                ListItems(
                    modifier = modifier.padding(5.dp),
                    items = itemTypeState.value.itemsTypeToolsAndOthers!!,
                    viewModel = viewModel,
                    navController = navController
                )
            }
            itemTypeState.value.itemsTypeOtherItems != null->{
                ListItems(
                    modifier = modifier.padding(5.dp),
                    items = itemTypeState.value.itemsTypeOtherItems!!,
                    viewModel = viewModel,
                    navController = navController
                )
            }
            itemTypeState.value.error?.isNotBlank() == true ->{
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        text = itemTypeState.value.error.toString(),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            else->{
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        text = itemTypeState.value.error.toString(),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
        }
}