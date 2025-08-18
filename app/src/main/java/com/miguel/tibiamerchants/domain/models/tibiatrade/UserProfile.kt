package com.miguel.tibiamerchants.domain.models.tibiatrade

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.domain.models.Profile
import com.miguel.tibiamerchants.domain.models.Trade
import com.miguel.tibiamerchants.domain.models.navigation.NavigationTibiaTrade
import com.miguel.tibiamerchants.presentation.Components.ItemTrade
import com.miguel.tibiamerchants.presentation.Components.Toolbar
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import com.miguel.tibiamerchants.utils.Dates

@Composable
fun UserGeneralData(
    modifier: Modifier = Modifier,
    tibia: Trade,
    user: String,
    varified: Boolean,
    createdAt: String
) {
    Surface(
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                Text(
                    text = tibia.userName,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                if(varified){
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_verified_24),
                        contentDescription = "Verified",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .size(20.dp)
                    )
                }
            }

            Text(
                text = "User since: ${Dates().format(createdAt).get()}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
            Surface(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                shape = MaterialTheme.shapes.large
            ) {
                Text(
                    text = "See verified characters",
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


@Composable
fun AdsHeader(modifier: Modifier = Modifier, text:String= "Ads Active") {
    Column(modifier = modifier) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 5.dp, top = 20.dp, bottom = 5.dp, end = 5.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        HorizontalDivider(
            modifier = Modifier.padding(5.dp)
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun UserAdsActive(
    modifier: Modifier = Modifier,
    tibia: Profile? = null,
    navigate: NavHostController = NavHostController(LocalContext.current)
) {
    BoxWithConstraints {
        val colum = when {
            maxWidth < 600.dp -> 1
            maxWidth >= 600.dp && maxWidth < 840.dp -> 2
            maxWidth >= 840.dp && maxWidth < 1200.dp -> 3
            maxWidth >= 1200.dp && maxWidth < 1600.dp -> 4
            maxWidth >= 1600.dp -> 5
            else -> 3
        }
        when{
            maxWidth < 600.dp -> {
                LazyVerticalGrid(
                    modifier = modifier,
                    columns = GridCells.Fixed(count = colum)
                ) {
                    item {
                        Column {
                            UserGeneralData(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth(),
                                tibia = tibia!!.ads[0],
                                user = tibia.ads[0].userName,
                                varified = tibia.isVerified,
                                createdAt = tibia.createdAt
                            )
                            AdsHeader(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth(),
                            )
                        }
                    }
                    tibia?.ads?.let{trade->
                        items(trade.size){
                            ItemTrade(
                                modifier = Modifier
                                    .padding(5.dp),
                                tibia = trade[it],
                                onClick = {
                                    navigate.navigate(NavigationTibiaTrade.routeWithId(trade[it].id))
                                },
                                hightLight = !trade[it].highlightedUntil.isNullOrEmpty()
                            )
                        }
                    }
                }
            }
            else->{
                Row(modifier = modifier.fillMaxWidth().padding(5.dp)){
                    UserGeneralData(
                        modifier = Modifier
                            .padding(5.dp).weight(0.30f),
                        tibia = tibia!!.ads[0],
                        user = tibia.ads[0].userName,
                        varified = tibia.isVerified,
                        createdAt = tibia.createdAt
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        AdsHeader(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                        )
                        LazyVerticalGrid(
                            modifier = modifier.fillMaxWidth(),
                            columns = GridCells.Fixed(count = colum)
                        ) {
                            tibia?.ads?.let {trade->
                                items(trade.size){
                                    ItemTrade(
                                        modifier = Modifier
                                            .padding(5.dp),
                                        tibia = trade[it],
                                        hightLight = !trade[it].highlightedUntil.isNullOrEmpty()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES, device = "id:pixel_5"
)
@Composable
fun PreviewUSerTibiaTrade(){
    TibiaMerchantsTheme {
        Scaffold { innerPadding ->
            val tibia = Trade(
                id = 94085,
                userId = 8459,
                type = 0,
                itemId = null ?: 0, // Tu modelo no admite null aquí, así que podrías cambiarlo a Int? si quieres manejar null
                itemTier = 0,
                currencyType = 0,
                price = 150000000,
                worldId = 70,
                isClosed = 0,
                isRookgaard = false,
                itemAmount = 1,
                houseId = 845,
                highlightedUntil = null,
                createdAt = "2025-06-26T09:39:05.299Z",
                itemName = null,
                itemLook = "", // No hay info en la data, puedes poner una URL por defecto o icono vacío
                worldName = "Talera",
                worldPvpType = "Open PvP",
                worldBattleyeColor = "yellow",
                userName = "peruano",
                isWhatsappVerified = 0, // No viene en tu JSON, así que pongo 0
                avatar = "rashid",
                tibiaId = 35061,
                houseName = "Market Street 4 (Shop)",
                size = 109,
                rent = 800000,
                beds = 3,
                floors = 3,
                rooms = 9,
                windows = 9,
                town = "Venore",
                coordinates = "32961,32047,6:6",
                isGuildhall = false,
                furnitures = "8 Lit Candles, 4 Wall Lamps e Counter (Venore).",
                likes = "0",
                isUserVerified = true,
                convertedPrice = 4003
            )
            val profile = Profile(
                ads = listOf(tibia) as ArrayList<Trade>,
                avatar = "rashid",
                isVerified = true,
                createdAt = "2025-06-26T09:39:05.299Z",
            )
            Column (modifier = Modifier.padding(innerPadding)){
                Toolbar("Profile")
                UserAdsActive(
                    modifier = Modifier
                        .padding(paddingValues = innerPadding),
                    tibia = profile,
                )
            }
        }
    }
}