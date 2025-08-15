package com.miguel.tibiamerchants.presentation.fragments

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.presentation.Components.Toolbar
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme


@Composable
fun TibiaTradeItemDetails(){
    Column {
        Toolbar(title = "Item Detail")
        TibiaTradeItemDetails(
            modifier = Modifier.fillMaxWidth()
        )
    }
}


/**
 * Currence type: 0; price in gold; converted_price in TC
 * Currence type: 1; price in TC; converted_price in gold; aveces no tiene converted_price o viene en cero
 * currence type: 2; not prices
 *
 * */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TibiaTradeItemDetails(modifier:Modifier = Modifier){
    LazyColumn {
        item{
            Column (modifier= modifier){
//        GlideImage(
//            model = "https://tibiatrade.gg/images/item/Sanguine_Rod.gif",
//            contentDescription = "Tibia Coins",
//            modifier = Modifier.align(Alignment.CenterHorizontally).size(100.dp),
//            alignment = Alignment.Center
//        )
                Surface(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
                    color = MaterialTheme.colorScheme.background,
                    tonalElevation = 4.dp
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.rashid),
                            contentDescription = "Tibia Coins",
                        )
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
                                    text = "2000",
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
                text ="Bear Soul Core",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "You see a bear soul core.\nOffers a soul to the Soulpit. Combine with an exalted core to turn it into a lesser soul core.",
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
                        "Wintera",
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
                        "Retro Open PvP",
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
                        "Peruano",
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
                        "350,000,000.00 golds",
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
                        "World",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(0.5f),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Wintera",
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
                        "7,845 TC",
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
                        "2025-08-14T23:57:09.244Z",
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
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally).fillMaxWidth()
                        .padding(5.dp)

                ) {
                    Text(text= "Shared")
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp, bottom = 10.dp, start = 5.dp, end = 5.dp).fillMaxWidth()

                ) {
                    Text(text= "Contact seller")
                }
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
                TibiaTradeItemDetails()
            }
        }
    }
}