package com.miguel.tibiamerchants.presentation.Components

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import com.miguel.tibiamerchants.BuildConfig
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.domain.models.ConverterPriceModel
import com.miguel.tibiamerchants.domain.models.TibiaTradeItemProfileModelDto
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelTibiaTrade
import com.miguel.tibiamerchants.utils.Dates
import com.miguel.tibiamerchants.utils.Money

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
                        state.data?.tibiaId?.let {
                            val img = state.data.imageUrl
                            Log.d("Tibia Iamge", img?: "")
                            AsyncImage(
                                model = img,
                                modifier = Modifier.size(200.dp),
                                contentDescription = "image house"
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
                    text = state.data?.houseName ?: "",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                state.data?.rent?.let {
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
                                text ="${state.data?.size.toString()} sqm's",
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
                                text ="${state.data?.rooms}",
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
                                text ="${state.data?.beds}",
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
                                text ="${state.data?.windows}",
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
                                text ="${state.data?.town}",
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
                                text = """Is Guildhall: ${if (state.data?.guildhall == true) "Yes" else "No"}""",
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
                        data = state.data.roomsImages,
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
                        state.data?.worldName ?: "",
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
                        state.data?.worldPvpType ?: "",
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
                        state.data?.username ?: "",
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
                        state.data?.beds.toString(),
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
                        state.data?.floors.toString(),
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
                        state.data?.windows.toString(),
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
                        state.data?.furnitures ?: "",
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
                state.data?.price ?: 0L,
                state.data?.convertedPrice ?: 0L,
                state.data?.currencyType ?: 0
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
                        state.data?.worldName ?: "",
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
                        text = state.data?.createdAt?.takeIf { it.isNotEmpty() }?.let { Dates().format(date = it).get() } ?: "",
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
                            val name = state.data?.itemName?.replace(" ", "-")
                            val item = "${name}-${state.data?.id}"
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
                        val name = state.data?.itemName?.replace(" ", "-")
                        val item = "${name}-${state.data?.id}"
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
    val sampleItem = TibiaTradeItemProfileModelDto(
        id = 171692,
        houseName = "Cliffside",
        tibiaId = 53001,
        imageUrl = "https://tibiatrade.gg/images/house/location/53001",
        size = 160,
        rent = 600000,
        beds = 6,
        floors = 4,
        rooms = 5,
        windows = 0,
        town = "Moonfall",
        coordinates = "0,0,0:0",
        furnitures = "Nenhuma.",
        worldName = "Ourobra",
        worldPvpType = "Open PvP",
        username = "emidechefe",
        price = 0,
        currencyType = 0,
        createdAt = "2026-10-01T19:10:37.748Z"
    )
    HouseProfile(state = ViewModelTibiaTrade.UIStateItem(data = sampleItem))
}