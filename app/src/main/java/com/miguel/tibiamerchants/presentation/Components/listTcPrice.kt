package com.miguel.tibiamerchants.presentation.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguel.tibiamerchants.domain.models.PriceModel
import com.miguel.tibiamerchants.domain.models.PriceTcModel
import java.text.NumberFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ListTcPrice(state: PriceTcModel) {
    val prices = state.prices
    LazyColumn {
        item{
            Column {
                Text(
                    text = "What is this?",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "You can find below the update prices in gold for Tibia Coins by game world. These are not exact prices, but a daily average. If you find any outdated prices, please contact us on our contact email.",
                    style = MaterialTheme.typography.bodyMedium
                )
                HorizontalDivider(
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
        items(prices.size){
            ItemListTC(
                modifier = Modifier
                .fillMaxWidth(),
                prices[it]
            )
            HorizontalDivider(
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
fun ItemListTC(modifier: Modifier, price: PriceModel){
    val instant = Instant.parse(price.createdAt)
    val zonedDateTime = instant.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    val formattedDate = zonedDateTime.format(formatter)
    val usFormatter = NumberFormat.getCurrencyInstance(Locale.US)
    Surface (modifier = modifier){
        Column() {
            Row {
                Text(
                    text = "World",
                    modifier = Modifier.weight(0.5f).padding(5.dp),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Created: $formattedDate",
                    modifier = Modifier.weight(1f).padding(5.dp),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.End
                )
            }
            Text(
                price.worldName,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 5.dp)
            )
            Text(
                text = "Buy average price: ${usFormatter.format(price.buyAveragePrice)} gold coins",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 5.dp)
            )
            Text(
                text = "Buy highest price: ${usFormatter.format(price.buyHighestPrice)} gold coins",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 5.dp)
            )
            Text(
                text = "Sell lowest price: ${usFormatter.format(price.sellLowestPrice)} gold coins",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(start = 5.dp)
            )
            Text(
                text = "Sell average price: ${usFormatter.format(price.sellAveragePrice)} gold coins",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    //ListTcPrice(state)
//    ItemListTC(
//        modifier = Modifier
//            .fillMaxWidth()
//    )
}