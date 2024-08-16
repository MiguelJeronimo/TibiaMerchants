package com.miguel.tibiamerchants

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.miguel.tibiamerchants.Models.BodyItemstype
import com.miguel.tibiamerchants.Models.BodyItemstypeWeapon
import com.miguel.tibiamerchants.Models.HouseHold
import com.miguel.tibiamerchants.Models.HouseHoldModel
import com.miguel.tibiamerchants.Models.ItemsModelsType
import com.miguel.tibiamerchants.Models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.Models.PostItemsType
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
                println("Title: ${titleState.value}")
                println("Name: ${nameState.value}")
                when(titleState.value?.lowercase()){
                    "body equipment"-> viewModel.setItems(PostItemsType(titleState.value, nameState.value))
                    "weapons"-> viewModel.setItemsWeapons(PostItemsType(titleState.value, nameState.value))
                    "household items"-> viewModel.setItemsHouseHold(PostItemsType(titleState.value, nameState.value))
                    "plants, animal products, food and drink"->{}
                    "tools and other equipment"->{}
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

@Composable
fun ListItems(modifier: Modifier, body: ArrayList<BodyItemstype>?){
    LazyColumn(modifier = modifier) {
        items(body!!.size){item->
            CardItems(
                modifier = Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                item = body[item]
            )
        }
    }
}

@Composable
fun ListItems(modifier: Modifier, items: ItemsModelsTypeWeapons) {
    println("WEAPONS ${items.body?.weapons}")
    println("WEAPONS ${items.body?.weaponsChargedReplicas}")
    println("WEAPONS ${items.body?.weaponsEnchantedReplicas}")
    LazyColumn(modifier = modifier) {
        val weapons = items.body?.weapons
        if (!items.body?.weapons.isNullOrEmpty()) {
            item {
                Column {
                    Text(
                        text = "Weapons",
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            items(weapons!!.size) { item ->
                CardItems(
                    modifier = Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    item = weapons[item]
                )
            }
        }
        if (!items.body?.weaponsChargedReplicas.isNullOrEmpty()){
            val weaponsChargedReplicas = items.body?.weaponsChargedReplicas
            item {
                Column {
                    Text(
                        text = "Charged Replicas",
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            items(weaponsChargedReplicas!!.size){item->
                CardItems(
                    modifier = Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    item = weaponsChargedReplicas[item]
                )
            }
        }
        if (!items.body?.weaponsEnchantedReplicas.isNullOrEmpty()){
            val weaponsChargedReplicas = items.body?.weaponsChargedReplicas
            item {
                Column {
                    Text(
                        text = "Charged Replicas",
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
                }
            }
            items(weaponsChargedReplicas!!.size){item->
                CardItems(
                    modifier = Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                    item = weaponsChargedReplicas[item]
                )
            }
        }
    }
}

@Composable
fun ListItems(modifier: Modifier, items: HouseHoldModel) {
    LazyColumn(modifier = modifier) {
        val houseHold = items.body
        item {
            Column {
                Text(
                    text = "Household Items",
                    Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
            }
        }
        items(houseHold?.items!!.size) { item ->
            CardItems(
                modifier = Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp),
                item = houseHold.items[item]
            )
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItems(modifier: Modifier, item: BodyItemstype?){
    /**
     *.fillMaxWidth(1f)
     *             .padding(16.dp)
     * */
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth(1f)){
                GlideImage(model = item?.img, contentDescription ="itemtibia",
                    Modifier
                        .size(80.dp)
                        .padding(10.dp))
                Column(Modifier.padding(16.dp)) {
                    Text(
                        //Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
                        text = item?.name!!,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))
            if (!item?.weight.isNullOrEmpty()){
                OutlinedCard {
                    Column(Modifier.padding(16.dp)) {
                        if (!item!!.arm.isNullOrEmpty()){
                            Text(text = "Arm: ${item.arm!!}")
                        }
                        if (!item.defense.isNullOrEmpty()){
                            Text(text = "Defense: ${item.defense}")
                        }
                        if (!item.vol.isNullOrEmpty()) {
                            Text(text = "Vol: ${item.vol}")
                        }
                        if (!item.weight.isNullOrEmpty()) {
                            Text(text = "Weight: ${item.weight}")
                        }
                        if (!item.attributes.isNullOrEmpty()) {
                            Text(text = "Attributes: ${item.attributes}")
                        }
                        if (!item.resist.isNullOrEmpty()) {
                            Text(text = "Resist: ${item.resist}")
                        }
                        if (!item.slots.isNullOrEmpty()) {
                            Text(text = "Slots: ${item.slots}")
                        }
                        if (!item.classs.isNullOrEmpty()) {
                            Text(text = "Class: ${item.classs}")
                        }
                        if (!item.level.isNullOrEmpty()) {
                            Text(text = "Level: ${item.level}")
                        }
                        if (!item.vocation.isNullOrEmpty()) {
                            Text(text = "Vocation: ${item.vocation}")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}


/*
* Cards of body items
**/
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItems(modifier: Modifier, item: BodyItemstypeWeapon?){
    /**
     *.fillMaxWidth(1f)
     *             .padding(16.dp)
     * */
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth(1f)){
                GlideImage(model = item?.image, contentDescription ="itemtibia",
                    Modifier
                        .size(80.dp)
                        .padding(10.dp))
                Column(Modifier.padding(16.dp)) {
                    Text(
                        //Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
                        text = item?.name!!,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))
            OutlinedCard {
                Column(Modifier.padding(16.dp)) {
                    if (!item?.level.isNullOrEmpty()){
                        Text(text = "Level: ${item?.level!!}")
                    }
                    if (!item?.attack.isNullOrEmpty()){
                        Text(text = "Attack: ${item?.attack!!}")
                    }
                    if (!item?.defense.isNullOrEmpty()){
                        Text(text = "Defense: ${item?.defense!!}")
                    }
                    if (!item?.defMode.isNullOrEmpty()){
                        Text(text = "Def. Mode: ${item?.defMode!!}")
                    }
                    if (!item?.hands.isNullOrEmpty()){
                        Text(text = "Hands: ${item?.hands!!}")
                    }
                    if (!item?.atkMode.isNullOrEmpty()){
                        Text(text = "Attack Mode: ${item?.atkMode!!}")
                    }
                    if (!item?.hit.isNullOrEmpty()){
                        Text(text = "Hit: ${item?.hit!!}")
                    }
                    if (!item?.embuimentSlots.isNullOrEmpty()){
                        Text(text = "Slots: ${item?.embuimentSlots!!}")
                    }
                    if (!item?.atk.isNullOrEmpty()){
                        Text(text = "Attack: ${item?.atk!!}")
                    }

                    if (!item?.damage.isNullOrEmpty()){
                        Text(text = "Damage: ${item?.damage}")
                    }
                    if (item?.damageType != null) {
                        Row {
                            GlideImage(
                                model = item.damageType?.imageIcon,
                                contentDescription ="itemtibia",
                                Modifier
                                    .size(35.dp)
                                    .padding(0.dp, 10.dp, 10.dp, 10.dp))
                            Text(text = item.damageType?.damageName!!, Modifier.align(Alignment.CenterVertically))
                        }
                    }
                    if (!item?.range.isNullOrEmpty()) {
                        Text(text = "Range: ${item?.range}")
                    }
                    if (!item?.mana.isNullOrEmpty()) {
                        Text(text = "Mana: ${item?.mana}")
                    }
                    if (!item?.resist.isNullOrEmpty()) {
                        Text(text = "Resist: ${item?.resist}")
                    }
                    if (!item?.slots.isNullOrEmpty()) {
                        Text(text = "Slots: ${item?.slots}")
                    }
                    if (!item?.classs.isNullOrEmpty()) {
                        Text(text = "Class: ${item?.classs}")
                    }
                    if (!item?.weight.isNullOrEmpty()) {
                        Text(text = "Weight: ${item?.weight}")
                    }
                    if (!item?.attributes.isNullOrEmpty()) {
                        Text(text = "Attributes: ${item?.attributes}")
                    }
                    if (!item?.npcPrice.isNullOrEmpty()){
                        Text(text = "Npc Price: ${item?.level!!}")
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItems(modifier: Modifier, item: HouseHold){
    /**
     *.fillMaxWidth(1f)
     *             .padding(16.dp)
     * */
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth(1f)){
                GlideImage(model = item.img, contentDescription ="itemtibia",
                    Modifier
                        .size(80.dp)
                        .padding(10.dp))
                Column(Modifier.padding(16.dp)) {
                    Text(
                        //Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
                        text = item.name!!,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))
            OutlinedCard {
                Column(Modifier.padding(16.dp)) {
                    if (!item.price.isNullOrEmpty()){
                        Text(text = "Price: ${item.price}")
                    }
                    if (!item.vol.isNullOrEmpty()){
                        Text(text = "Vol: ${item.vol}")
                    }
                    if (!item.weight.isNullOrEmpty()){
                        Text(text = "Weight: ${item.weight}")
                    }
                    if (!item.slots.isNullOrEmpty()){
                        Text(text = "Slots: ${item.slots}")
                    }
                    if (!item.weightPerVol.isNullOrEmpty()){
                        Text(text = "Weight Per Vol: ${item.weightPerVol}")
                    }
                    if (!item.buyFrom.isNullOrEmpty()){
                        Text(text = "Buy From: ${item.buyFrom}")
                    }
                    if (!item.light.isNullOrEmpty()){
                        Text(text = "Light: ${item.light}")
                    }
                    if (!item.writable.isNullOrEmpty()){
                        Text(text = "Writable: ${item.writable}")
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
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