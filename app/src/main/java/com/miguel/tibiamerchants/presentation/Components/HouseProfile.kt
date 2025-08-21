package com.miguel.tibiamerchants.presentation.Components

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.miguel.tibiamerchants.BuildConfig
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.domain.models.ConverterPriceModel
import com.miguel.tibiamerchants.domain.models.TibiaTradeItemModel
import com.miguel.tibiamerchants.domain.models.Trade
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelTibiaTrade
import com.miguel.tibiamerchants.utils.Dates
import com.miguel.tibiamerchants.utils.Money

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HouseProfile(
    modifier: Modifier = Modifier,
    state: ViewModelTibiaTrade.UIStateItem,
    onRetry: () -> Unit = {},
) {
    val context = LocalContext.current
    LazyColumn {
        item {
            Column(modifier = modifier) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
                    color = MaterialTheme.colorScheme.background,
                    tonalElevation = 4.dp
                ) {
                    Box(modifier = Modifier.wrapContentSize()) {
                        state.data?.ad?.tibiaId?.let {
                            val img = "${BuildConfig.API_TIBIA_TRADE}images/house/location/${it}"
                            Log.d("Tibia Iamge", img)
                            AsyncImage(
                                model = img,
                                modifier = Modifier.size(200.dp),
                                contentDescription = "image house"
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .align(Alignment.BottomEnd)
                        ) {
                            Surface(
                                modifier = Modifier,
                                shape = MaterialTheme.shapes.medium,
                            ) {
                                Text(
                                    text = state.data?.ad?.likes!!,
                                    modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.labelSmall
                                )

                            }
                            Icon(
                                imageVector = Icons.Filled.FavoriteBorder,
                                tint = MaterialTheme.colorScheme.error,
                                contentDescription = "Likes",
                                modifier = Modifier
                                    .padding(top = 5.dp, end = 5.dp, bottom = 5.dp)
                                    .size(25.dp)
                            )
                        }
                    }
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
            }
        }
        item {
            Column(modifier = modifier) {
                Text(
                    text = state.data?.ad?.houseName.toString(),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                state.data?.ad?.rent?.let {
                    Text(
                        text = "Rent: ${Money().format(it.toDouble()).get()} golds",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(5.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Yellow
                    )
                }
                Column (
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp).fillMaxWidth()
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Row(modifier = Modifier.weight(0.5f)){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_square_24),
                                modifier = Modifier
                                    .padding(5.dp).size(25.dp),
                                contentDescription = "Likes"
                            )
                            Text(
                                text ="${state.data?.ad?.size.toString()} sqm's",
                                modifier = Modifier
                                    .padding(5.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.End
                            )
                        }
                        Row(modifier = Modifier.weight(0.5f)){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_bedroom_parent_24),
                                modifier = Modifier
                                    .padding(5.dp).size(25.dp),
                                contentDescription = "Likes"
                            )
                            Text(
                                text ="${state.data?.ad?.rooms}",
                                modifier = Modifier
                                    .padding(5.dp),
                                style = MaterialTheme.typography.labelLarge,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Row(modifier = Modifier.weight(0.5f)){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_bed_24),
                                modifier = Modifier
                                    .padding(5.dp).size(25.dp),
                                contentDescription = "Likes"
                            )
                            Text(
                                text ="${state.data?.ad?.beds}",
                                modifier = Modifier
                                    .padding(5.dp),
                                style = MaterialTheme.typography.labelLarge,
                                textAlign = TextAlign.End
                            )
                        }
                        Row(modifier = Modifier.weight(0.5f)){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_window_24),
                                modifier = Modifier
                                    .padding(5.dp).size(25.dp),
                                contentDescription = "Likes"
                            )
                            Text(
                                text ="${state.data?.ad?.windows}",
                                modifier = Modifier
                                    .padding(5.dp),
                                style = MaterialTheme.typography.labelLarge,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Row(modifier = Modifier.weight(0.5f)){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_location_on_24),
                                modifier = Modifier
                                    .padding(5.dp).size(25.dp),
                                contentDescription = "Likes"
                            )
                            Text(
                                text ="${state.data?.ad?.town}",
                                modifier = Modifier
                                    .padding(5.dp),
                                style = MaterialTheme.typography.labelLarge,
                                textAlign = TextAlign.End
                            )
                        }
                        Row(modifier = Modifier.weight(0.5f)){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_warehouse_24),
                                modifier = Modifier
                                    .padding(5.dp).size(25.dp),
                                contentDescription = "Likes"
                            )
                            Text(
                                text = """Is Guildhall: ${if (state.data?.ad?.isGuildhall == true) "Yes" else "No"}""",
                                modifier = Modifier
                                    .padding(5.dp),
                                style = MaterialTheme.typography.labelLarge,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
                if (state.data?.screenshotCount != null && state.data.screenshotCount > 0) {
                    CarroucelHouse(
                        data = state.data,
                        modifier = Modifier
                            .fillMaxWidth()
                            //.wrapContentHeight()
                            .padding(5.dp)
                    )
                } else {
                    Text(
                        text = "There are no images of the rooms available.",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(5.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
            }
        }
        item {
            Column(modifier = modifier) {
                Text(
                    text = "Attributes",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        "World",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        state.data?.ad?.worldName!!,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        "Server Type",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        state.data?.ad?.worldPvpType!!,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        "User",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        state.data?.ad?.userName!!,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
        item {
            Column(modifier = modifier) {
                Text(
                    text = "Features",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        "Beds",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        state.data?.ad?.beds.toString(),
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        "Floors",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        state.data?.ad?.floors.toString(),
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        "Windows",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        state.data?.ad?.windows.toString(),
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        "Furniture's",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        state.data?.ad?.furnitures!!,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
        item {
            val converter = ConverterPriceModel().convert(
                state.data?.ad?.price!!,
                state.data?.ad?.convertedPrice ?: 0,
                state.data?.ad?.currencyType!!
            ).get()
            Column(modifier = modifier) {
                Text(
                    text = "Converter Price",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        "Price in Tibia",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        converter.price,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.End,
                        color = if (converter.price.contains("golds")) Color.Yellow else if (converter.price.contains(
                                "TC"
                            )
                        ) Color.Green else Color.Unspecified
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        "World",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        state.data?.ad?.worldName!!,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        "Converter Price",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        converter.converter,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.End,
                        color = if (converter.converter.contains("golds")) Color.Yellow else if (converter.converter.contains(
                                "TC"
                            )
                        ) Color.Green else Color.Unspecified
                    )
                }
            }
        }
        item {
            Column(modifier = modifier) {
                Text(
                    text = "Additional Attributes",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        "Create at",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = Dates().format(date = state.data?.ad?.createdAt!!).get(),
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
        item {
            Column(modifier = modifier) {
                Button(
                    onClick = {
                        val sendIntent: Intent = Intent().apply {
                            val name = state.data?.ad?.itemName?.replace(" ", "-")
                            val item = "${name}-${state.data?.ad?.id}"
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "${BuildConfig.API_TIBIA_TRADE}trade/$item")
                            type = "text/plain"
                        }

                        val shareIntent =
                            Intent.createChooser(sendIntent, "Hello this is shared item")
                        context.startActivity(shareIntent)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(5.dp)

                ) {
                    Text(text = "Shared")
                }
                Button(
                    onClick = {
                        val name = state.data?.ad?.itemName?.replace(" ", "-")
                        val item = "${name}-${state.data?.ad?.id}"
                        val url = "${BuildConfig.API_TIBIA_TRADE}trade/$item"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
                        .fillMaxWidth()

                ) {
                    Text(text = "Contact seller")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHouseProfile() {
    val tradeItem = TibiaTradeItemModel(
        isClosed = false,
        screenshotCount = 4,
        hasFeedImage = false,
        hasStoryImage = false,
        hasFeedPtBrImage = false,
        hasStoryPtBrImage = false,
        ad = Trade(
            id = 101108,
            userId = 8271,
            type = 0,
            itemId = 0, // null en JSON, lo adapto a 0
            itemTier = 0,
            currencyType = 0,
            price = 0,
            worldId = 30,
            isClosed = 0,
            isRookgaard = false,
            itemAmount = 1,
            houseId = 901,
            highlightedUntil = null,
            createdAt = "2025-08-14T23:01:24.664Z",
            itemName = null,
            itemLook = null, // no venía en JSON
            worldName = "Havera",
            worldPvpType = "Open PvP",
            worldBattleyeColor = "yellow",
            userName = "petus",
            isWhatsappVerified = 0, // no venía en JSON
            avatar = "rashid",
            tibiaId = 37010,
            houseName = "Luminous Arc 2",
            size = 154,
            rent = 600000,
            beds = 4,
            floors = 4,
            rooms = 6,
            windows = 14,
            town = "Yalahar",
            coordinates = "32843,31212,7:5",
            isGuildhall = false,
            furnitures = "6 Walls Lamps.",
            likes = "0", // en JSON era número
            isUserVerified = true,
            convertedPrice = 0
        )
    )
    HouseProfile(state = ViewModelTibiaTrade.UIStateItem(data = tradeItem))
}