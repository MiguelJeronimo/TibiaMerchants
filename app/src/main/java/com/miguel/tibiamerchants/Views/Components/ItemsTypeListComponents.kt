package com.miguel.tibiamerchants.Views.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.miguel.tibiamerchants.Models.BodyItemstype
import com.miguel.tibiamerchants.Models.BodyItemstypeWeapon
import com.miguel.tibiamerchants.Models.HouseHold
import com.miguel.tibiamerchants.Models.HouseHoldModel
import com.miguel.tibiamerchants.Models.ItemOtherPlants
import com.miguel.tibiamerchants.Models.ItemsModelsTypeWeapons
import com.miguel.tibiamerchants.Models.OtherItem
import com.miguel.tibiamerchants.Models.OtherItemsModel
import com.miguel.tibiamerchants.Models.PlantsAnimalsProductsFoodDrink
import com.miguel.tibiamerchants.Models.ToolsAndOtherEquipment
import com.miguel.tibiamerchants.Models.ToolsAndOtherEquipmentModel

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
//others items list
@Composable
fun ListItems(modifier: Modifier, items: PlantsAnimalsProductsFoodDrink) {
    LazyColumn(modifier = modifier) {
        val others = items.body
        item {
            Column {
                Text(
                    text = "Plants, Animal Products, Food and Drink",
                    Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
            }
        }
        items(others?.items!!.size) { item ->
            CardItem(
                modifier = modifier,
                item = others.items[item]
            )
        }
    }
}

//Tools and other Equipment
@Composable
fun ListItems(modifier: Modifier, items: ToolsAndOtherEquipmentModel) {
    LazyColumn(modifier = modifier) {
        val tools = items.body
        item {
            Column {
                Text(
                    text = "Tools and other Equipment",
                    Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
            }
        }
        items(tools?.items!!.size) { item ->
            CardItems(
                modifier = modifier, item = tools.items[item]
            )
        }
    }
}

//Tools and other Equipment
@Composable
fun ListItems(modifier: Modifier, items: OtherItemsModel) {
    LazyColumn(modifier = modifier) {
        val tools = items.body
        item {
            Column {
                Text(
                    text = "Tools and other Equipment",
                    Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider(Modifier.padding(16.dp, 5.dp, 16.dp, 5.dp))
            }
        }
        items(tools?.items!!.size) { item ->
            CardItems(
                modifier = modifier, item = tools.items[item]
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
            if (!item.weight.isNullOrEmpty()){
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
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItem(modifier: Modifier, item: ItemOtherPlants){
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
            if (!item.weight.isNullOrEmpty()){
                OutlinedCard {
                    Column(Modifier.padding(16.dp)) {
                        if (!item.price.isNullOrEmpty()){
                            Text(text = "Price: ${item.price}")
                        }
                        if (!item.price.isNullOrEmpty()){
                            Text(text = "Buy From: ${item.price}")
                        }
                        if (!item.attributes.isNullOrEmpty()){
                            Text(text = "Light: ${item.attributes}")
                        }
                        if (!item.weight.isNullOrEmpty()){
                            Text(text = "Weight: ${item.weight}")
                        }
                        if (!item.writable.isNullOrEmpty()){
                            Text(text = "Writable: ${item.writable}")
                        }
                        if (!item.stackable.isNullOrEmpty()){
                            Text(text = "Weight: ${item.weight}")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItems(modifier: Modifier, item: ToolsAndOtherEquipment?){
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
                        if (!item?.level.isNullOrEmpty()){
                            Text(text = "Level: ${item?.level!!}")
                        }
                        if (!item?.arm.isNullOrEmpty()){
                            Text(text = "Arm: ${item?.arm!!}")
                        }
                        if (!item?.resist.isNullOrEmpty()){
                            Text(text = "Resist: ${item?.resist!!}")
                        }
                        if (!item?.duration.isNullOrEmpty()){
                            Text(text = "Duration: ${item?.duration!!}")
                        }
                        if (!item?.charges.isNullOrEmpty()){
                            Text(text = "Charges: ${item?.charges!!}")
                        }
                        if (!item?.attributes.isNullOrEmpty()){
                            Text(text = "Attributes: ${item?.attributes!!}")
                        }
                        if (!item?.weight.isNullOrEmpty()){
                            Text(text = "Weight: ${item?.weight!!}")
                        }
                        if (!item?.vocation.isNullOrEmpty()){
                            Text(text = "Vocation: ${item?.vocation!!}")
                        }

                        if (!item?.writable.isNullOrEmpty()){
                            Text(text = "Writable: ${item?.writable}")
                        }
                        if (item?.radius != null) {
                            Text(text = "Radius: ${item.radius}")
                        }
                        if (!item?.sellForNPC.isNullOrEmpty()) {
                            Text(text = "Sell for NPC: ${item?.sellForNPC}")
                        }
                        if (!item?.buyForNPC.isNullOrEmpty()) {
                            Text(text = "Buy for NPC: ${item?.buyForNPC}")
                        }
                        if (!item?.value.isNullOrEmpty()) {
                            Text(text = "Value: ${item?.value}")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItems(modifier: Modifier, item: OtherItem?){
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
                        if (!item?.level.isNullOrEmpty()){
                            Text(text = "Level: ${item?.level!!}")
                        }
                        if (!item?.magicLevel.isNullOrEmpty()){
                            Text(text = "Magic Level: ${item?.magicLevel!!}")
                        }
                        if (!item?.type.isNullOrEmpty()){
                            Text(text = "Type: ${item?.type!!}")
                        }
                        if (!item?.npcPrice.isNullOrEmpty()){
                            Text(text = "Npc Price: ${item?.npcPrice!!}")
                        }
                        if (!item?.arm.isNullOrEmpty()){
                            Text(text = "Arm: ${item?.arm!!}")
                        }
                        if (!item?.resist.isNullOrEmpty()){
                            Text(text = "Resist: ${item?.resist!!}")
                        }
                        if (!item?.duration.isNullOrEmpty()){
                            Text(text = "Duration: ${item?.duration!!}")
                        }
                        if (!item?.charges.isNullOrEmpty()){
                            Text(text = "Charges: ${item?.charges!!}")
                        }
                        if (!item?.attributes.isNullOrEmpty()){
                            Text(text = "Attributes: ${item?.attributes!!}")
                        }
                        if (!item?.weight.isNullOrEmpty()){
                            Text(text = "Weight: ${item?.weight!!}")
                        }
                        if (!item?.vocation.isNullOrEmpty()){
                            Text(text = "Vocation: ${item?.vocation!!}")
                        }

                        if (!item?.writable.isNullOrEmpty()){
                            Text(text = "Writable: ${item?.writable}")
                        }
                        if (item?.radius != null) {
                            Text(text = "Radius: ${item.radius}")
                        }
                        if (!item?.sellForNPC.isNullOrEmpty()) {
                            Text(text = "Sell for NPC: ${item?.sellForNPC}")
                        }
                        if (!item?.buyForNPC.isNullOrEmpty()) {
                            Text(text = "Buy for NPC: ${item?.buyForNPC}")
                        }
                        if (!item?.value.isNullOrEmpty()) {
                            Text(text = "Value: ${item?.value}")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}