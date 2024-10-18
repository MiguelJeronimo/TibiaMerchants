package com.miguel.tibiamerchants.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
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
import com.miguel.tibiamerchants.presentation.Components.ListItems
import com.miguel.tibiamerchants.presentation.Components.Toolbar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModeltemsType
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelItemsTypeFactory
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import org.koin.android.ext.android.inject

class Itemsype : ComponentActivity() {
    private lateinit var viewModel: ViewModeltemsType

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
            TibiaMerchantsTheme {
                val titleState = rememberSaveable { mutableStateOf(title) }
                val nameState = rememberSaveable { mutableStateOf(name) }
                val listItems = remember { mutableStateOf(ArrayList<BodyItemstype>()) }
                val listItemsWapons = remember { mutableStateOf(ItemsModelsTypeWeapons()) }
                val listItemsHouseHold = remember { mutableStateOf(HouseHoldModel()) }
                val listPlantsAnimalsProductsFoodDrink = remember { mutableStateOf(
                    PlantsAnimalsProductsFoodDrink()
                ) }
                val listToolsAndOtherEquipment = remember { mutableStateOf(
                    ToolsAndOtherEquipmentModel()
                ) }
                val listOtherItems = remember { mutableStateOf(OtherItemsModel()) }
                println("Title: ${titleState.value}")
                println("Name: ${nameState.value}")
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
                viewModel.items.observe(this, Observer {
                    if (it != null){
                        listItems.value = it.body!!
                    }
                    else{
                        viewModel.setProgressBar(false)
                    }
                })

                viewModel.itemsTypeWeapons.observe(this, Observer {
                    if (it != null) {
                        listItemsWapons.value = it
                    } else {
                        viewModel.setProgressBar(false)
                    }
                })

                viewModel.itemsTypeHouseHold.observe(this, Observer {
                    println("Viewmodel ${it}")
                    if (it != null) {
                        listItemsHouseHold.value = it
                    } else {
                        viewModel.setProgressBar(false)
                    }
                })

                viewModel.plantsAnimalsProductsFoodDrink.observe(this, Observer {
                    if (it != null) {
                        listPlantsAnimalsProductsFoodDrink.value = it
                    } else {
                        viewModel.setProgressBar(false)
                    }
                })

                viewModel.itemsTypeToolsAndOthers.observe(this, Observer {
                    if (it != null) {
                        listToolsAndOtherEquipment.value = it
                    } else {
                        viewModel.setProgressBar(false)
                    }
                })

                viewModel.itemsTypeOtherItems.observe(this, Observer {
                    if (it != null) {
                        listOtherItems.value = it
                    } else {
                        viewModel.setProgressBar(false)}
                })
                viewModel.back.observe(this, Observer {
                    if (it){
                        finish()
                    }
                })

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Toolbar(nameState.value.toString(), viewModel)
                        if (listItems.value.isNotEmpty()){
                            ListItems(
                                modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                                body = listItems.value
                            )
                        }
                        if (listItemsWapons.value.body != null){
                            ListItems(
                                modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                                items = listItemsWapons.value
                            )
                        }

                        if (listItemsHouseHold.value.body != null){
                            ListItems(
                                modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                                items = listItemsHouseHold.value
                            )
                        }

                        if (listPlantsAnimalsProductsFoodDrink.value.body != null){
                            ListItems(
                                modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                                items = listPlantsAnimalsProductsFoodDrink.value
                            )
                        }

                        if (listToolsAndOtherEquipment.value.body != null){
                            ListItems(
                                modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                                items = listToolsAndOtherEquipment.value
                            )
                        }
                        if (listOtherItems.value.body != null){
                            ListItems(
                                modifier = Modifier.padding(5.dp, 10.dp, 10.dp, 5.dp),
                                items = listOtherItems.value
                            )
                        }
                    }
                }
            }
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