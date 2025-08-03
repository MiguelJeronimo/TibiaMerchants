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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.miguel.tibiamerchants.presentation.Components.Toolbar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModeltemsType
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelItemsTypeFactory
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
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

                viewModel.back.observe(this, Observer {
                    if (it) {
                        finish()
                    }
                })

                Scaffold(modifier = Modifier) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        //Toolbar(nameState.value.toString(), viewModel)
                        if (progressState.value) {
                            ProgressIndicatorItemsType()
                        }

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


@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    TibiaMerchantsTheme {
        //CardItems(modifier = Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp), null)
    }
}