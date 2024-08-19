package com.miguel.tibiamerchants

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
import com.miguel.tibiamerchants.Models.BodyItemstype
import com.miguel.tibiamerchants.Models.HouseHoldModel
import com.miguel.tibiamerchants.Models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.Models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.Models.PostItemsType
import com.miguel.tibiamerchants.Models.ToolsAndOtherEquipmentModel
import com.miguel.tibiamerchants.Views.Components.ListItems
import com.miguel.tibiamerchants.Views.Components.Toolbar
import com.miguel.tibiamerchants.Views.ViewModels.ViewModeltemsType
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme

class Itemsype : ComponentActivity() {
    private lateinit var viewModel: ViewModeltemsType

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = intent.getStringExtra("title")
        val name = intent.getStringExtra("name")
        viewModel = ViewModelProvider(this)[ViewModeltemsType::class.java]
        enableEdgeToEdge()
        setContent {
            TibiaMerchantsTheme {
                val titleState = rememberSaveable { mutableStateOf(title) }
                val nameState = rememberSaveable { mutableStateOf(name) }
                val listItems = remember { mutableStateOf(ArrayList<BodyItemstype>()) }
                val listItemsWapons = remember { mutableStateOf(ItemsModelsTypeWeapons()) }
                val listItemsHouseHold = remember { mutableStateOf(HouseHoldModel()) }
                val listPlantsAnimalsProductsFoodDrink = remember { mutableStateOf(PlantsAnimalsProductsFoodDrink()) }
                val listToolsAndOtherEquipment = remember { mutableStateOf(ToolsAndOtherEquipmentModel()) }
                println("Title: ${titleState.value}")
                println("Name: ${nameState.value}")
                when(titleState.value?.lowercase()){
                    "body equipment"-> viewModel.setItems(PostItemsType(titleState.value, nameState.value))
                    "weapons"-> viewModel.setItemsWeapons(PostItemsType(titleState.value, nameState.value))
                    "household items"-> viewModel.setItemsHouseHold(PostItemsType(titleState.value, nameState.value))
                    "plants, animal products, food and drink"-> viewModel.setPlantsAnimalsProductsFoodDrink(PostItemsType(titleState.value, nameState.value))
                    "tools and other equipment"-> viewModel.setItemsToolsAndOthers(PostItemsType(titleState.value, nameState.value))
                    "other items"->{}
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

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Toolbar(nameState.value.toString())
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
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    TibiaMerchantsTheme {
        //CardItems(modifier = Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp), null)
    }
}