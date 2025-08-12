package com.miguel.tibiamerchants.presentation.Components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(5.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
        items(prices.size){
            PriceCard(
                modifier = Modifier
                .fillMaxWidth().padding(5.dp),
                price = prices[it]
            )
        }
    }
}

fun percentageChange(buy: Int, sell: Int): Double {
    if (buy == 0) return 0.0
    return (sell - buy).toDouble() / buy.toDouble() * 100.0
}

@Composable
fun PriceCard(price: PriceModel, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val instant = Instant.parse(price.createdAt)
    val zonedDateTime = instant.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    val formattedDate = zonedDateTime.format(formatter)
    val usFormatter = NumberFormat.getCurrencyInstance(Locale.US)

    val diff = percentageChange(price.buyAveragePrice, price.sellAveragePrice)
    val isUp = diff >= 0
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        //elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            // Left: world name and timestamp
            Column(modifier = Modifier.weight(1f)) {
                Text(price.worldName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(4.dp))
                Text(formattedDate, style = MaterialTheme.typography.bodySmall)
            }

            // Middle: buy/sell values
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(start = 12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Buy:", style = MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.width(6.dp))
                    Text(usFormatter.format(price.buyAveragePrice), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold, color = Color(0xFF4CAF50))
                }
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Sell:", style = MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.width(6.dp))
                    Text(usFormatter.format(price.sellAveragePrice), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.error)
                }
            }

            // Right: small badge with percent change and arrow
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(start = 12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    androidx.compose.material3.Icon(
                        imageVector = if (isUp) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = if (isUp) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        String.format(Locale.getDefault(), "%.1f%%", kotlin.math.abs(diff)),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (isUp) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(8.dp))
                // mini details
                Text("Max Buy: ${usFormatter.format(price.buyHighestPrice)}", style = MaterialTheme.typography.bodySmall)
                Text("Min Sell: ${usFormatter.format(price.sellLowestPrice)}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}


@SuppressLint("ComposableNaming")
@Preview(showBackground = true)
@Composable
fun PreviewCardPrices() {
    val priceModel = PriceModel(
        worldName = "Elvenwood",
        buyAveragePrice = 32638,
        buyHighestPrice = 32638,
        sellLowestPrice = 32638,
        sellAveragePrice = 32638,
        createdAt = "2023-06-01T12:00:00Z"
    )
    PriceCard(
        modifier = Modifier.padding(16.dp),
        price = priceModel
    )
    Text("HOLA")
}