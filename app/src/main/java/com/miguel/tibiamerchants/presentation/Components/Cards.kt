package com.miguel.tibiamerchants.presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme

@Composable
fun CardHeaderItemInfo() {
    Box(Modifier.fillMaxWidth().padding(10.dp)) {
        Card (
            modifier = Modifier.padding(start = 35.dp)
        ){
            Column(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(5.dp),
            ) {
                Text(
                    text = "Demon Shield",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Level: 400",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 3.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Vocation: Paladin",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 3.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Clasification: Body Equipment",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 3.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.swift_shop_logo),
            contentDescription = "demon logo",
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(80.dp)
                .clip(CircleShape)
                .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
    }
}


//Card details
@Composable
fun CardDetails() {
    Card(
        modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight(),
    ) {
        Column {
            GeneralPropierties()
            HorizontalDivider(
                modifier = Modifier.padding(
                    top = 5.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 5.dp
                ),
            )
            CompatPropierties()
            HorizontalDivider(
                modifier = Modifier.padding(
                    top = 5.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 5.dp
                ),
            )
            TradePropierties()
            HorizontalDivider(
                modifier = Modifier.padding(
                    top = 5.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 5.dp
                ),
            )
            FieldPropierties()
        }
    }
}

@Composable
fun CardNotes(){
    OutlinedCard(
        modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight(),
    ) {
        Column {
            Text(
                text = "Notes",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Part of the Alicorn Set.\nIt is a possible reward of the Primal Ordeal Quest, which guarantees one random item from the Primal Set through the Hazard System.\n",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun CardRequeriments(){
    Card(
        modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Requeriments",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Level: 33",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Vocation: sorcerers",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Magic level: 34",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun CardOtherPropierties(){
    Card(
        modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Other Propierties",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Version: 7.6 December 12, 2005 Christmas Update 2005",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun CardMagicPropierties(){
    ElevatedCard(
        modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Magic Propierties",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Words: exevo gran mas flam",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun CardTibiaLegends(){
    OutlinedCard(
        modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "You see a wand of inferno. Classification: 1 Tier: 0. It can only be wielded properly by sorcerers of level 33 or higher. It weighs 27.00 oz. It unleashes the very fires of hell.",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun GeneralPropierties(){
    Box (
        Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier.padding(5.dp).align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.github),
                contentDescription = "github logo",
                modifier = Modifier
                    .size(20.dp).align(Alignment.CenterVertically)
                    //.clip(CircleShape)
                    .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Text(
                modifier = Modifier.padding(5.dp).align(Alignment.CenterVertically),
                text = "General Propierties",
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
                .padding(
                    top = 45.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Weight: 39.0 oz",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Set Part: Alicon Set",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Column {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Pickuable: x",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Stackeable: x",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
fun CompatPropierties(){
    Box (
        Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier.padding(5.dp).align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.github),
                contentDescription = "github logo",
                modifier = Modifier
                    .size(20.dp).align(Alignment.CenterVertically)
                    //.clip(CircleShape)
                    .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Text(
                modifier = Modifier.padding(5.dp).align(Alignment.CenterVertically),
                text = "Combat Propierties",
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
                .padding(
                    top = 45.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            Column(
                modifier = Modifier
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Embuing Slots: 2",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Upgrade Classification: 4",
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Attributes: distance fighting +3",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(5.dp).align(Alignment.End),
                    text = "Armor: 11",
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    modifier = Modifier.padding(5.dp).align(Alignment.End),
                    text = "Protection/Resist: fire +5%, earth +5%, energy +5%, ice +5%, holy +5%, death +5%, physical +5%",
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    modifier = Modifier.padding(5.dp).align(Alignment.End),
                    text = "Element: x",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
fun TradePropierties(){
    Box (
        Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier.padding(5.dp).align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.github),
                contentDescription = "github logo",
                modifier = Modifier
                    .size(20.dp).align(Alignment.CenterVertically)
                    //.clip(CircleShape)
                    .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Text(
                modifier = Modifier.padding(5.dp).align(Alignment.CenterVertically),
                text = "Trade Propierties",
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
                .padding(
                    top = 45.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            Column(
                modifier = Modifier
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Marketable: X",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Value: Negotiable gp gp",
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Sold for: (not bought by NPCs)",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(5.dp).align(Alignment.End),
                    text = "Bought for: (not sold by NPCs)",
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    modifier = Modifier.padding(5.dp).align(Alignment.End),
                    text = "Loot value: 50000 gol coins",
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    modifier = Modifier.padding(5.dp).align(Alignment.End),
                    text = "Store price: 50 tc",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
fun FieldPropierties(){
    Box (
        Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier.padding(5.dp).align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.github),
                contentDescription = "github logo",
                modifier = Modifier
                    .size(20.dp).align(Alignment.CenterVertically)
                    //.clip(CircleShape)
                    .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Text(
                modifier = Modifier.padding(5.dp).align(Alignment.CenterVertically),
                text = "Field Propierties",
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
                .padding(
                    top = 45.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            Column(
                modifier = Modifier
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Blocking: X",
                    style = MaterialTheme.typography.labelSmall

                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(5.dp).align(Alignment.End),
                    text = "Light: 3 sqm",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

/// chips
@Composable
fun ChipFilter(text: String, state: MutableState<Boolean>?){
    var selected by rememberSaveable { mutableStateOf(true) }
    state?.value = selected
    FilterChip(
        modifier = Modifier.padding(5.dp, 0.dp, 5.dp, 0.dp),
        onClick = {
            selected = !selected
            state?.value = selected
        },
        label = {
            Text(text)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}

//CardsList buy from, sell from
@Composable
fun CardBuyFrom(){
    Card(
        modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight()
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Haroun",
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp).fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Location: Ashta'daramai",
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp).fillMaxWidth(),
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Row(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gold_icon),
                    contentDescription = "gold logo",
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = "Price: 10000000 gp",
                    modifier = Modifier
                        .padding(10.dp),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
@Composable
fun CardSellFrom(){
    Card(
        modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight()
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Haroun",
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp).fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Location: Ashta'daramai",
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp).fillMaxWidth(),
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Row(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gold_icon),
                    contentDescription = "gold logo",
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = "Price: 10000000 gp",
                    modifier = Modifier
                        .padding(10.dp),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun Observar() {
    TibiaMerchantsTheme {
        Column {
            CardHeaderItemInfo()
            CardNotes()
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                ChipFilter("Buy for", null)
                ChipFilter("Sell to", null)
            }
            //CardDetails()
            CardRequeriments()
            CardOtherPropierties()
            CardMagicPropierties()
            CardBuyFrom()
            CardSellFrom()
        }
    }
}