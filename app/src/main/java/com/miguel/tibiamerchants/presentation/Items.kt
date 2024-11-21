package com.miguel.tibiamerchants.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.miguel.tibiamerchants.domain.models.Data
import com.miguel.tibiamerchants.domain.models.ItemsModels
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.presentation.Components.Toolbar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelItems
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelItemsFactory
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject

class Items : ComponentActivity() {

    lateinit var viewModel: ViewModelItems
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
                viewModel.items.observe(this, Observer {
                    println("ITEMS: "+it)
                    if (it != null){
                        stateList.value = it
                    } else{
                            Toast.makeText(this, "Error, conection time out", Toast.LENGTH_SHORT).show()
                    }
                    viewModel.setProgressBar(false)
                })

                viewModel.isVisibleProgressBar.observe(this, Observer {
                    stateProgressBar.value = it
                })

                Scaffold(
                    modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection),
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Toolbar("Items", viewmodel = viewModel)
                        if (stateProgressBar.value){
                            StatusBar()
                        }
                        SwipeRefresh(stateList, viewModel, pullToRefreshState)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeRefresh(
    stateList: MutableState<ItemsModels>,
    viewModel: ViewModelItems,
    pullToRefreshState: PullToRefreshState,
) {
    val stateProgress = remember { mutableStateOf(false) }
    if (pullToRefreshState.isRefreshing) {
        viewModel.setProgressBar(true)
        LaunchedEffect(true) {
            viewModel.setItems()
            delay(1500)
            viewModel.setProgressBar(false)
            pullToRefreshState.endRefresh()
        }
    }
    //while to SwipeRefresh is executing
    if (pullToRefreshState.progress>0.0){
        stateProgress.value = true
    }

    Box(
        Modifier
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
            .fillMaxSize()
    ) {
        if (!pullToRefreshState.isRefreshing) {
            GridLayoutItems(stateList, viewModel)
        }
        if (stateProgress.value){
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullToRefreshState
            )
            stateProgress.value = false
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
@Composable
fun GridLayoutItems(items: MutableState<ItemsModels>, post: ViewModelItems) {
    LazyColumn(Modifier.fillMaxSize()) {
        if (items.value.body != null){
            //bodyEquipment
            item {
                Column {
                    Text(
                        text = items.value.body?.bodyEquipment!!.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val bodyEquipment = items.value.body!!.bodyEquipment.array
            items(bodyEquipment.size){npc->
                CardItems(
                    bodyEquipment[npc],
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    tittle = items.value.body?.bodyEquipment!!.title,
                    post
                )
            }
            //weapons
            item {
                Column {
                    Text(
                        text = items.value.body!!.weapons.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val weapons = items.value.body!!.weapons.array
            items(weapons.size){npc->
                CardItems(
                    weapons[npc],
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    tittle = items.value.body!!.weapons.title,
                    post
                )
            }
            //householdItems
            item {
                Column {
                    Text(
                        text = items.value.body!!.householdItems.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val householdItems = items.value.body!!.householdItems.array
            items(householdItems.size){npc->
                CardItems(
                    householdItems[npc],
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    tittle = items.value.body!!.householdItems.title,
                    post
                )
            }
            //others
            item {
                Column {
                    Text(
                        text = items.value.body!!.others.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val others = items.value.body!!.others.array
            items(others.size){npc->
                CardItems(
                    others[npc],
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    tittle = items.value.body!!.others.title,
                    post
                )
            }
            //toolsEquipment
            item {
                Column {
                    Text(
                        text = items.value.body!!.toolsEquipment.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val toolsEquipment = items.value.body!!.toolsEquipment.array
            items(toolsEquipment.size){npc->
                CardItems(
                    toolsEquipment[npc],
                    Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    tittle = items.value.body!!.toolsEquipment.title,
                    post
                )
            }
            //otherItems
            item {
                Column {
                    Text(
                        text = items.value.body!!.otherItems.title,
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            val otherItems = items.value.body!!.otherItems.array
            items(otherItems.size){npc->
                CardItems(
                    otherItems[npc],
                    modifier = Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    tittle = items.value.body!!.otherItems.title,
                    post
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItems(data: Data, modifier: Modifier, tittle: String, post: ViewModelItems) {
    Card(
        modifier = modifier,
        onClick = {
            post.setPost(PostItemsType(tittle, data.name))
        }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    TibiaMerchantsTheme {
        Greeting("Android")
    }
}