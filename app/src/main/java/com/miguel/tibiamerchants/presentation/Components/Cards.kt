package com.miguel.tibiamerchants.presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.miguel.tibia_merchants_api.domain.models.BuyFrom
import com.miguel.tibia_merchants_api.domain.models.CombatPropierties
import com.miguel.tibia_merchants_api.domain.models.FieldPropierties
import com.miguel.tibia_merchants_api.domain.models.GeneralPropierties
import com.miguel.tibia_merchants_api.domain.models.MagicProperties
import com.miguel.tibia_merchants_api.domain.models.OtherPropierties
import com.miguel.tibia_merchants_api.domain.models.Profile
import com.miguel.tibia_merchants_api.domain.models.Requeriments
import com.miguel.tibia_merchants_api.domain.models.SellFrom
import com.miguel.tibia_merchants_api.domain.models.TraderPropierties
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardHeaderItemInfo(profile: Profile? = null) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
        Card (
            modifier = Modifier.padding(start = 35.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(5.dp),
            ) {
                println("Profileeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee: ${profile?.name}")
                println("Profileeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee: $profile")
                profile?.name?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 5.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                if (profile?.requeriments != null){
                    profile.requeriments?.level.let{
                        Text(
                            text = "Level: $it",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 3.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    profile.requeriments?.vocation.let {
                        Text(
                            text = "Vocation: $it",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 3.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                }
                if (profile?.generalPropierties != null){
                    profile.generalPropierties?.classification.let {
                        Text(
                            text = "Classification: $it",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 3.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }

        profile?.img?.let {
            GlideImage(
                model = it,
                contentDescription = "demon logo",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
        }
    }
}


//Card details
@Composable
fun CardDetails(profile: Profile? = null) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column {
            profile?.generalPropierties?.let {
                CardGeneralPropierties(profile.generalPropierties)
                HorizontalDivider(
                    modifier = Modifier.padding(
                        top = 5.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 5.dp
                    )
                )
            }

            profile?.combatPropierties?.let {
                CompatPropierties(profile.combatPropierties)
                HorizontalDivider(
                    modifier = Modifier.padding(
                        top = 5.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 5.dp
                    ),
                )
            }

            profile?.traderPropierties?.let{
                TradePropierties(profile.traderPropierties)
                HorizontalDivider(
                    modifier = Modifier.padding(
                        top = 5.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 5.dp
                    ),
                )
            }

            profile?.fieldPropierties?.let {
                CardFieldPropierties(profile.fieldPropierties)
            }
        }
    }
}

@Composable
fun CardNotes(profile: Profile? = null) {
    OutlinedCard(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column {

            Text(
                text = "Notes",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp),
                style = MaterialTheme.typography.titleMedium
            )

            profile?.notes?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun CardRequeriments(requeriments: Requeriments? = null){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
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

            requeriments?.level?.let {
                Text(
                    text = "Level: $it",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            requeriments?.vocation?.let {
                Text(
                    text = "Vocation: $it",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            requeriments?.magic_level?.let {
                Text(
                    text = "Magic level: $it",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun CardOtherPropierties(otherPropierties: OtherPropierties? = null) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
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
            otherPropierties?.version?.let {
                Text(
                    text = "Version: $it",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun CardMagicPropierties( magicProperties: MagicProperties? = null){
    ElevatedCard(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
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
            magicProperties?.let {
                Text(
                    text = "Words: $it",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun CardTibiaLegends(tibiaLegend:String? = null){
    OutlinedCard(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            tibiaLegend?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun CardGeneralPropierties(generalPropierties: GeneralPropierties? = null) {
    Box (
        Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.github),
                contentDescription = "github logo",
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
                    //.clip(CircleShape)
                    .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterVertically),
                text = "General Propierties",
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(
                    top = 45.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
               generalPropierties?.weight?.let {
                   Text(
                       modifier = Modifier.padding(5.dp),
                       text = "Weight: $it",
                       style = MaterialTheme.typography.labelSmall
                   )
               }
                generalPropierties?.also_known_as?.let {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Set Part: $it",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                generalPropierties?.item_class?.let {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Item Class: $it",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            Column {
                //x
                generalPropierties?.pickupable?.let {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Pickuable: $it",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                generalPropierties?.stackable?.let {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Stackeable: x",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                generalPropierties?.origin?.let {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Origin: $it",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
fun CompatPropierties(combatPropierties: CombatPropierties? = null) {
    Box (
        Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.github),
                contentDescription = "github logo",
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
                    //.clip(CircleShape)
                    .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterVertically),
                text = "Combat Propierties",
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(
                    top = 45.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            Column(
                modifier = Modifier
            ) {

                combatPropierties?.imbuing_slots?.let {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Embuing Slots: 2",
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                combatPropierties?.upgrade_classification?.let {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Upgrade Classification: 4",
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                combatPropierties?.attributes?.let {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Attributes: distance fighting +3",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                combatPropierties?.armor?.let{
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.End),
                        text = "Armor: $it",
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                combatPropierties?.resists?.let{
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.End),
                        text = "Protection/Resist: $it",
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                combatPropierties?.element?.let{
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.End),
                        text = "Element: $it",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
fun TradePropierties(traderPropierties: TraderPropierties?= null){
    Box (
        Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.github),
                contentDescription = "github logo",
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
                    //.clip(CircleShape)
                    .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterVertically),
                text = "Trade Propierties",
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(
                    top = 45.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            Column(
                modifier = Modifier
            ) {
                traderPropierties?.marketable?.let {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Marketable: $it",
                        style = MaterialTheme.typography.labelSmall
                    )
                }

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
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End),
                    text = "Bought for: (not sold by NPCs)",
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End),
                    text = "Loot value: 50000 gol coins",
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End),
                    text = "Store price: 50 tc",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
fun CardFieldPropierties(fieldPropierties: FieldPropierties? = null){
    Box (
        Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.github),
                contentDescription = "github logo",
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
                    //.clip(CircleShape)
                    .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterVertically),
                text = "Field Propierties",
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
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
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End),
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
fun CardBuyFrom(buyFrom: BuyFrom? = null){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                buyFrom?.npc?.let{
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                buyFrom?.location?.let {
                    Text(
                        text = "Location: $it",
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
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
                buyFrom?.price?.let {
                    Text(
                        text = "Price: $it gp",
                        modifier = Modifier
                            .padding(10.dp),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}
@Composable
fun CardSellFrom(sellFrom: SellFrom? = null){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                sellFrom?.npc?.let{
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                sellFrom?.location?.let {
                    Text(
                        text = "Location: $it",
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
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
                sellFrom?.price?.let {
                    Text(
                        text = "Price: $it gp",
                        modifier = Modifier
                            .padding(10.dp),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
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