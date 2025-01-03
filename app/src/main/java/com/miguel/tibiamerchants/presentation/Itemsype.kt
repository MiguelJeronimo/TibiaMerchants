package com.miguel.tibiamerchants.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.models.BodyItemstype
import com.miguel.tibiamerchants.domain.models.HouseHoldModel
import com.miguel.tibiamerchants.domain.models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.domain.models.OtherItemsModel
import com.miguel.tibiamerchants.domain.models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.models.ToolsAndOtherEquipmentModel
import com.miguel.tibiamerchants.domain.models.spells.ResponseSpells
import com.miguel.tibiamerchants.presentation.Components.Toolbar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModeltemsType
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelItemsTypeFactory
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject

class Itemsype : ComponentActivity() {
    private lateinit var viewModel: ViewModeltemsType

    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = intent.getStringExtra("title")
        val name = intent.getStringExtra("name")
        val factory: ViewModelItemsTypeFactory by inject()
        viewModel = ViewModelProvider(this, factory)[ViewModeltemsType::class.java]
        enableEdgeToEdge()
        setContent {
            val pullToRefreshState = rememberPullToRefreshState()
            TibiaMerchantsTheme {
                val titleState = rememberSaveable { mutableStateOf(title) }
                val nameState = rememberSaveable { mutableStateOf(name) }
                val listItems = remember { mutableStateOf(ArrayList<BodyItemstype>()) }
                val listItemsWapons = remember { mutableStateOf(ItemsModelsTypeWeapons()) }
                val listItemsHouseHold = remember { mutableStateOf(HouseHoldModel()) }

                val spellsDataState = remember { mutableStateOf(ResponseSpells()) }
                val progressState = remember { mutableStateOf(false) }

                val listPlantsAnimalsProductsFoodDrink = remember {
                    mutableStateOf(
                        PlantsAnimalsProductsFoodDrink()
                    )
                }
                val listToolsAndOtherEquipment = remember {
                    mutableStateOf(
                        ToolsAndOtherEquipmentModel()
                    )
                }
                val listOtherItems = remember { mutableStateOf(OtherItemsModel()) }
                when (titleState.value?.lowercase()) {
                    "body equipment" -> viewModel.setItems(
                        PostItemsType(
                            titleState.value,
                            nameState.value
                        )
                    )

                    "weapons" -> viewModel.setItemsWeapons(
                        PostItemsType(
                            titleState.value,
                            nameState.value
                        )
                    )

                    "household items" -> viewModel.setItemsHouseHold(
                        PostItemsType(
                            titleState.value,
                            nameState.value
                        )
                    )

                    "plants, animal products, food and drink" -> viewModel.setPlantsAnimalsProductsFoodDrink(
                        PostItemsType(titleState.value, nameState.value)
                    )

                    "tools and other equipment" -> viewModel.setItemsToolsAndOthers(
                        PostItemsType(
                            titleState.value,
                            nameState.value
                        )
                    )

                    "other items" -> viewModel.setItemsOtherItems(
                        PostItemsType(
                            titleState.value,
                            nameState.value
                        )
                    )

                    else -> {
                        viewModel.setItems(PostItemsType(titleState.value, nameState.value))
                    }
                }

                viewModel.name.observe(this) { name ->
                    if (name != null) {
                        Intent(this, ItemProfile::class.java).also {
                            it.putExtra("name", name)
                            startActivity(it)
                        }
                    }
                }

                viewModel.isVisibleProgressBar.observe(this) {
                    if (it != null) {
                        println("isVisibleProgressBar: $it")
                        progressState.value = it
                    }
                }

                viewModel.items.observe(this, Observer {
                    if (it != null) {
                        listItems.value = it.body!!
                    }
                    viewModel.setProgressBar(false)
                })

                viewModel.itemsTypeWeapons.observe(this, Observer {
                    if (it != null) {
                        listItemsWapons.value = it
                    }
                    viewModel.setProgressBar(false)
                })

                viewModel.itemsTypeHouseHold.observe(this, Observer {
                    if (it != null) {
                        listItemsHouseHold.value = it
                    }
                    viewModel.setProgressBar(false)
                })

                viewModel.plantsAnimalsProductsFoodDrink.observe(this, Observer {
                    if (it != null) {
                        listPlantsAnimalsProductsFoodDrink.value = it
                    }
                    viewModel.setProgressBar(false)
                })

                viewModel.itemsTypeToolsAndOthers.observe(this, Observer {
                    if (it != null) {
                        listToolsAndOtherEquipment.value = it
                    }
                    viewModel.setProgressBar(false)

                })

                viewModel.itemsTypeOtherItems.observe(this, Observer {
                    if (it != null) {
                        listOtherItems.value = it
                    }
                    viewModel.setProgressBar(false)
                })

                viewModel.back.observe(this, Observer {
                    if (it) {
                        finish()
                    }
                })

                Scaffold(modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Toolbar(nameState.value.toString(), viewModel)
                        if (progressState.value) {
                            ProgressIndicatorItemsType()
                        }
                        SwipeRefreshItemsType(
                            titleState = titleState,
                            nameState = nameState,
                            listItems = listItems,
                            listItemsWapons = listItemsWapons,
                            listItemsHouseHold = listItemsHouseHold,
                            listPlantsAnimalsProductsFoodDrink = listPlantsAnimalsProductsFoodDrink,
                            listToolsAndOtherEquipment = listToolsAndOtherEquipment,
                            listOtherItems = listOtherItems,
                            modifier = Modifier.padding(innerPadding),
                            viewModel = viewModel,
                            pullToRefreshState = pullToRefreshState
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.setName(null)
    }
}


@Composable
fun ProgressIndicatorItemsType() {
    LinearProgressIndicator(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 10.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeRefreshItemsType(
    listItems: MutableState<ArrayList<BodyItemstype>>,
    viewModel: ViewModeltemsType,
    pullToRefreshState: PullToRefreshState,
    modifier: Modifier,
    nameState: MutableState<String?>,
    listItemsWapons: MutableState<ItemsModelsTypeWeapons>,
    listItemsHouseHold: MutableState<HouseHoldModel>,
    listPlantsAnimalsProductsFoodDrink: MutableState<PlantsAnimalsProductsFoodDrink>,
    listToolsAndOtherEquipment: MutableState<ToolsAndOtherEquipmentModel>,
    listOtherItems: MutableState<OtherItemsModel>,
    titleState: MutableState<String?>,
) {
    val stateProgress = remember { mutableStateOf(false) }
    if (pullToRefreshState.isRefreshing) {
        viewModel.setProgressBar(true)
        LaunchedEffect(true) {
            when(titleState.value?.lowercase()){
                "body equipment"-> viewModel.setItems(PostItemsType(titleState.value, nameState.value))
                "weapons"-> viewModel.setItemsWeapons(PostItemsType(titleState.value, nameState.value))
                "household items"-> viewModel.setItemsHouseHold(PostItemsType(titleState.value, nameState.value))
                "plants, animal products, food and drink"-> viewModel.setPlantsAnimalsProductsFoodDrink(
                    PostItemsType(titleState.value, nameState.value)
                )
                "tools and other equipment"-> viewModel.setItemsToolsAndOthers(PostItemsType(titleState.value, nameState.value))
                "other items"-> viewModel.setItemsOtherItems(PostItemsType(titleState.value, nameState.value))
                else->{
                    viewModel.setItems(PostItemsType(titleState.value, nameState.value))
                }
            }
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
            if (listItems.value.isNotEmpty()){
                com.miguel.tibiamerchants.presentation.Components.ListItems(
                    modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                    body = listItems.value,
                    viewModel = viewModel
                )
            }

            if (listItemsWapons.value.body != null){
                com.miguel.tibiamerchants.presentation.Components.ListItems(
                    modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                    items = listItemsWapons.value,
                    viewModel = viewModel
                )
            }

            if (listItemsHouseHold.value.body != null){
                com.miguel.tibiamerchants.presentation.Components.ListItems(
                    modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                    items = listItemsHouseHold.value,
                    viewModel = viewModel
                )
            }

            if (listPlantsAnimalsProductsFoodDrink.value.body != null){
                com.miguel.tibiamerchants.presentation.Components.ListItems(
                    modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                    items = listPlantsAnimalsProductsFoodDrink.value,
                    viewModel = viewModel
                )
            }

            if (listToolsAndOtherEquipment.value.body != null){
                com.miguel.tibiamerchants.presentation.Components.ListItems(
                    modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                    items = listToolsAndOtherEquipment.value,
                    viewModel = viewModel
                )
            }

            if (listOtherItems.value.body != null){
                com.miguel.tibiamerchants.presentation.Components.ListItems(
                    modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                    items = listOtherItems.value,
                    viewModel = viewModel
                )
            }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    TibiaMerchantsTheme {
        //CardItems(modifier = Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp), null)
    }
}