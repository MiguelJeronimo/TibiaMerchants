package com.miguel.tibiamerchants.presentation.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.miguel.tibiamerchants.domain.models.Data
import com.miguel.tibiamerchants.domain.models.ItemsModels
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SwipeRefresh(
    viewModel: ViewModelItems = koinViewModel(),
    scope: CoroutineScope = rememberCoroutineScope(),
    scaffoldNavigator: ThreePaneScaffoldNavigator<PostItemsType>
) {
    val itemsInfo = viewModel.items.collectAsState()
    val corrutineScope = rememberCoroutineScope()
    val state = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        state = state,
        modifier = Modifier,
        onRefresh = {
            isRefreshing = true
            corrutineScope.launch {
                viewModel.setItems()
                delay(1500)
                isRefreshing = false
            }
        },
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                state = state
            )
        }

    ) {
        when{
            itemsInfo.value._isLoading->{
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

            itemsInfo.value.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        text = "Loading...",
                        modifier = Modifier.align(Alignment.Center).padding(10.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            else->{
                itemsInfo.value.items.let {
                    GridLayoutItems(it, viewModel, scope, scaffoldNavigator)
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun StatusBar() {
    LinearProgressIndicator(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 10.dp)
    )
}
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun GridLayoutItems(
    items: ItemsModels?,
    post: ViewModelItems,
    scope: CoroutineScope = rememberCoroutineScope(),
    scaffoldNavigator: ThreePaneScaffoldNavigator<PostItemsType>
) {
    LazyColumn(Modifier.fillMaxSize()) {
        if (items?.body != null){
            //bodyEquipment
            item {
                Column {
                    Text(
                        text = items?.body?.bodyEquipment!!.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val bodyEquipment = items?.body!!.bodyEquipment.array
            items(bodyEquipment.size){npc->
                CardItems(
                    bodyEquipment[npc],
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    onClick = {
                        val title = items?.body?.bodyEquipment!!.title
                        //post.setPost(PostItemsType(title, bodyEquipment[npc].name))
                        scope.launch {//send data in navigations
                            scaffoldNavigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                contentKey = PostItemsType(title, bodyEquipment[npc].name)
                            )
                        }
                    }
                )
            }
            //weapons
            item {
                Column {
                    Text(
                        text = items?.body!!.weapons.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val weapons = items?.body!!.weapons.array
            items(weapons.size){npc->
                CardItems(
                    weapons[npc],
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    onClick = {
                        val title = items?.body?.weapons!!.title
                        //post.setPost(PostItemsType(title, weapons[npc].name))
                        scope.launch {//send data in navigations
                            scaffoldNavigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                contentKey = PostItemsType(title, weapons[npc].name)
                            )
                        }
                    }
                )
            }
            //householdItems
            item {
                Column {
                    Text(
                        text = items?.body!!.householdItems.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val householdItems = items?.body!!.householdItems.array
            items(householdItems.size){npc->
                CardItems(
                    householdItems[npc],
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    onClick = {
                        val title = items?.body?.householdItems!!.title
                        //post.setPost(PostItemsType(title, householdItems[npc].name))
                        scope.launch {//send data in navigations
                            scaffoldNavigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                contentKey = PostItemsType(title, householdItems[npc].name)
                            )
                        }

                    }
                )
            }
            //others
            item {
                Column {
                    Text(
                        text = items?.body!!.others.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val others = items?.body!!.others.array
            items(others.size){npc->
                CardItems(
                    others[npc],
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    onClick = {
                        val title = items?.body?.others!!.title
                        //post.setPost(PostItemsType(title, others[npc].name))
                        scope.launch {//send data in navigations
                            scaffoldNavigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                contentKey = PostItemsType(title, others[npc].name)
                            )
                        }
                    }
                )
            }
            //toolsEquipment
            item {
                Column {
                    Text(
                        text = items?.body!!.toolsEquipment.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val toolsEquipment = items.body.toolsEquipment.array
            items(toolsEquipment.size){npc->
                CardItems(
                    toolsEquipment[npc],
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    onClick = {
                        val title = items.body.toolsEquipment.title
                        //post.setPost(PostItemsType(title, toolsEquipment[npc].name))
                        scope.launch {//send data in navigations
                            scaffoldNavigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                contentKey = PostItemsType(title, toolsEquipment[npc].name)
                            )
                        }
                    }
                )
            }
            //otherItems
            item {
                Column {
                    Text(
                        text = items.body.otherItems.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val otherItems = items.body.otherItems.array
            items(otherItems.size){npc->
                CardItems(
                    otherItems[npc],
                    modifier = Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    onClick = {
                        val title = items.body.otherItems.title
                        //post.setPost(PostItemsType(title, otherItems[npc].name))
                        scope.launch {//send data in navigations
                            scaffoldNavigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                contentKey = PostItemsType(title, otherItems[npc].name)
                            )
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItems(data: Data, modifier: Modifier, onClick: () -> Unit = {}) {
    Card(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(Modifier.fillMaxWidth(1f)){
            GlideImage(model = data.img, contentDescription ="itemtibia",
                Modifier
                    .size(80.dp)
                    .padding(10.dp))
            Column(Modifier.padding(16.dp)) {
                Text(
                    //Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
                    text = data.name,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}