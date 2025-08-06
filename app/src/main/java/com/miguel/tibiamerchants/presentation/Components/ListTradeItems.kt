package com.miguel.tibiamerchants.presentation.Components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import androidx.core.graphics.toColorInt
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.miguel.tibiamerchants.BuildConfig
import com.miguel.tibiamerchants.domain.models.Trade
import java.text.NumberFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.text.replace


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ItemTradeList(
    modifier: Modifier = Modifier,
    state: LazyPagingItems<Trade>,
) {
    BoxWithConstraints {
        val colum = when {
            maxWidth < 600.dp -> 1
            maxWidth >= 600.dp && maxWidth < 840.dp -> 3
            maxWidth >= 840.dp && maxWidth < 1200.dp -> 5
            maxWidth >= 1200.dp && maxWidth < 1600.dp -> 6
            maxWidth >= 1600.dp -> 7
            else -> 3
        }
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(count = colum)
        ) {
            items(state.itemCount) {
                if (state[it]?.town != null) {
                    Log.d(
                        "Town",
                        "${BuildConfig.API_TIBIA_TRADE}/images/house/location/${state[it]?.tibiaId}"
                    )
                }
                state[it]?.let {
                    ItemTrade(
                        modifier = Modifier
                            .padding(5.dp),
                        tibia = it,
                        hightLight = !it.highlightedUntil.isNullOrEmpty()
                    )
                }
            }
            if (state.itemCount < 24 && state.loadState.append is LoadState.NotLoading) {
                item {
                    Spacer(
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                    )
                }
            }

            if (state.loadState.append is LoadState.Loading) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Loading(
                            modifier = Modifier
                                .width(64.dp)
                                .align(Alignment.Center)
                                .padding(5.dp)
                        )
                    }
                }
            }
        }


    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemTrade(
    modifier: Modifier = Modifier,
    tibia: Trade,
    onClick: () -> Unit = {},
    onClickButtonUser: () -> Unit = {},
    hightLight: Boolean = false,
) {
    val instant = Instant.parse(tibia.createdAt)
    val zonedDateTime = instant.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formattedDate = zonedDateTime.format(formatter)
    val usFormatter = NumberFormat.getCurrencyInstance(Locale.US)
    OutlinedCard(
        modifier = modifier,
        onClick = onClick,
        border = if (hightLight) BorderStroke(2.dp, Color(0xFFB8A672)) else BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.surfaceVariant
        ),
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Surface(
                    modifier = Modifier.padding(5.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = if (tibia.type == 0) MaterialTheme.colorScheme.errorContainer
                    else MaterialTheme.colorScheme.primaryContainer//0 = selling, 1  buying
                ) {
                    Text(
                        text = if (tibia.type == 0) "Selling" else "Buying",
                        modifier = Modifier.padding(5.dp),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                Text(
                    text = formattedDate,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
            ElevatedCard(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Row {
                   //Validate if is article or house
                    tibia.itemName?.let {
                        val name = it.replace(" ", "_")
                        tibia.tibiaId?.let {
                            Log.d("DEBUG", "tibiaId: ${tibia.tibiaId}, town: ${tibia.town}")
                        }
                        val img = "${BuildConfig.API_TIBIA_TRADE}images/item/$name.gif"
                        GlideImage(
                            model = img,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(5.dp)
                                .size(100.dp),
                            failure = placeholder(R.drawable.error_image_icon),
                        )
                    }
                }
                tibia.tibiaId?.let {
                    val img = "${BuildConfig.API_TIBIA_TRADE}images/house/location/${it}"
                    Log.d("Tibia Iamge", img)
                    AsyncImage(
                        model = img,
                        modifier = Modifier.size(200.dp),
                        contentDescription = "image house"
                    )
                }
                Surface(
                    modifier = Modifier
                        .width(20.dp)
                        .padding(top = 5.dp, end = 5.dp),
                    color = MaterialTheme.colorScheme.errorContainer,
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Text(
                        text = tibia.itemTier.toString(),
                        modifier = Modifier.padding(5.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
        val text: String = (tibia.itemName ?: tibia.houseName.toString())
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )
        val textPrice = "Price: ${usFormatter.format(tibia.price)} gold coins; Tcs: ${
            tibia.convertedPrice?.let { usFormatter.format(it) }
        }"
        Text(
            text = if (tibia.price.toInt() == 0) "Talking offerts" else textPrice,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp),
        )
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.CenterHorizontally),
            shape = MaterialTheme.shapes.medium,
            //#B8A672
            color = if (tibia.worldBattleyeColor == "green".lowercase()) Color("#8EAC50".toColorInt()) else Color(
                "#B8A672".toColorInt()
            )
        ) {
            Text(
                text = "${tibia.worldName} (${tibia.worldPvpType})",
                modifier = Modifier.padding(5.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f),
                shape = MaterialTheme.shapes.medium,
                onClick = onClickButtonUser,
            ) {
                Text(
                    text = tibia.userName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall
                )
                Image(
                    painter = painterResource(id = R.drawable.baseline_verified_user_24),
                    contentDescription = "null",
                    //modifier = Modifier.size(15.dp),
                    alignment = Alignment.Center
                )
            }
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.Bottom)
            ) {
                Surface(
                    modifier = Modifier,
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Text(
                        text = tibia.likes,
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
}

@Preview(showBackground = true)
@Composable
fun PeviewCard() {
    //ItemTrade()
    TibiaMerchantsTheme {
//        ItemTradeList(modifier = Modifier.fillMaxSize(),)
    }
}