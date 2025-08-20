package com.miguel.tibiamerchants.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.miguel.tibiamerchants.BuildConfig
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.domain.models.ConverterPriceModel
import com.miguel.tibiamerchants.presentation.Components.ErrorMessage
import com.miguel.tibiamerchants.presentation.Components.HouseProfile
import com.miguel.tibiamerchants.presentation.Components.Loading
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelTibiaTrade
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme
import com.miguel.tibiamerchants.utils.Dates
import org.koin.androidx.compose.koinViewModel


@Composable
fun TibiaTradeItemDetails(
    modifier: Modifier = Modifier,
    id: String?,
    viewModel: ViewModelTibiaTrade = koinViewModel()
) {
    val state = viewModel.item.collectAsStateWithLifecycle()
    Log.d("state", state.value.toString())
    TibiaTradeItemDetails(
        modifier = modifier,
        state = state.value,
        onRetry = { viewModel.item(id?.toInt()) }
    )
}


/**
 * Currence type: 0; price in gold; converted_price in TC
 * Currence type: 1; price in TC; converted_price in gold; aveces no tiene converted_price o viene en cero
 * currence type: 2; not prices
 *
 * */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TibiaTradeItemDetails(modifier: Modifier = Modifier, state: ViewModelTibiaTrade.UIStateItem, onRetry: () -> Unit = {}){
    val context = LocalContext.current
    when{
        state.isLoding -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Loading(
                        modifier = Modifier
                            .width(64.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(5.dp)
                    )
                    Text(
                        text = "Loading...",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp)
                    )
                }
            }
        }
        state.error != null -> {
            Box(modifier = Modifier.fillMaxSize()){
                ErrorMessage(
                    messageHeader = "Ups!",
                    message = "Something went wrong trying to get the data",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    onRetry = onRetry
                )
            }
        }
        state.data != null -> {
            state.data.ad.itemId?.let{
                    LazyColumn {
                        item{
                            Column (modifier= modifier){
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
                                        state.data.ad.itemName?.let {
                                            val name = it.replace(" ", "_")
                                            val img = "${BuildConfig.API_TIBIA_TRADE}images/item/$name.gif"
                                            GlideImage(
                                                model = img,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .size(100
                                                        .dp),
                                                failure = placeholder(R.drawable.error_image_icon),
                                            )
                                        }
                                        Row(
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .align(Alignment.BottomEnd)
                                        ) {
                                            Surface(
                                                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                                                shape = MaterialTheme.shapes.medium,
                                            ) {
                                                Text(
                                                    text = state.data.ad.likes,
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
                                HorizontalDivider(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp))
                            }
                        }
                        item{
                            Column (modifier= modifier){
                                Text(
                                    text = state.data.ad.itemName.toString(),
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(5.dp),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = state.data.ad.itemLook!!,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 5.dp),
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Justify
                                )
                            }}
                        item{
                            Column (modifier= modifier){
                                Text(
                                    text = "Attributes",
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(5.dp),
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )

                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)){
                                    Text(
                                        "World",
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(0.5f),
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        state.data.ad.worldName,
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
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)){
                                    Text(
                                        "Server Type",
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(0.5f),
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        state.data.ad.worldPvpType,
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
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)){
                                    Text(
                                        "User",
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(0.5f),
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        state.data.ad.userName,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(1f),
                                        style = MaterialTheme.typography.labelLarge,
                                        textAlign = TextAlign.End
                                    )
                                }
                            }
                        }
                        item{
                            val converter = ConverterPriceModel().convert(
                                state.data.ad.price,
                                state.data.ad.convertedPrice?: 0,
                                state.data.ad.currencyType
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
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)){
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
                                        color = if(converter.price.contains("golds")) Color.Yellow else if(converter.price.contains("TC")) Color.Green else Color.Unspecified
                                    )
                                }
                                HorizontalDivider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp)
                                )
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)){
                                    Text(
                                        "World",
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(0.5f),
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        state.data.ad.worldName,
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

                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)){
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
                                        color = if(converter.converter.contains("golds")) Color.Yellow else if(converter.converter.contains("TC")) Color.Green else Color.Unspecified
                                    )
                                }
                            }
                        }
                        item{
                            Column(modifier = modifier) {
                                Text(
                                    text = "Additional Attributes",
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(5.dp),
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)){
                                    Text(
                                        "Create at",
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(0.5f),
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = Dates().format(date = state.data.ad.createdAt).get(),
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(1f),
                                        style = MaterialTheme.typography.labelLarge,
                                        textAlign = TextAlign.End
                                    )
                                }
                            }
                        }
                        item{
                            Column(modifier = modifier) {
                                Button(
                                    onClick = {
                                        val sendIntent: Intent = Intent().apply {
                                            val name = state.data.ad.itemName?.replace(" ", "-")
                                            val item = "${name}-${state.data.ad.id}"
                                            action = Intent.ACTION_SEND
                                            putExtra(Intent.EXTRA_TEXT, "${BuildConfig.API_TIBIA_TRADE}trade/$item")
                                            type = "text/plain"
                                        }

                                        val shareIntent = Intent.createChooser(sendIntent, "Hello this is shared item")
                                        context.startActivity(shareIntent)
                                    },
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .fillMaxWidth()
                                        .padding(5.dp)

                                ) {
                                    Text(text= "Shared")
                                }
                                Button(
                                    onClick = {
                                        val name = state.data.ad.itemName?.replace(" ", "-")
                                        val item = "${name}-${state.data.ad.id}"
                                        val url = "${BuildConfig.API_TIBIA_TRADE}trade/$item"
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                        context.startActivity(intent)
                                    },
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(top = 5.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
                                        .fillMaxWidth()

                                ) {
                                    Text(text= "Contact seller")
                                }
                            }
                        }
                    }
                }
            state.data.ad.houseId?.let{
                    Log.d("House State", "${state.data.ad}")
                    HouseProfile(
                        state = state,
                        modifier = modifier
                    )
                }
            }
        }
}

//preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES, device = "id:pixel_5"
)
@Composable
fun PreviewTibiaTradeItemDetails(){
    TibiaMerchantsTheme {
        Scaffold {innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                TibiaTradeItemDetails(
                    state = ViewModelTibiaTrade.UIStateItem()
                )
            }
        }
    }
}