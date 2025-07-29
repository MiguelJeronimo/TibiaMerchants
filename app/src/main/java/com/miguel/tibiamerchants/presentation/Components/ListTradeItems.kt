package com.miguel.tibiamerchants.presentation.Components

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguel.tibiamerchants.R

@Composable
fun ItemTrade(modifier:Modifier = Modifier){
//    val instant = Instant.parse("")
//    val zonedDateTime = instant.atZone(ZoneId.systemDefault())
//    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
//    val formattedDate = zonedDateTime.format(formatter)
//    val usFormatter = NumberFormat.getCurrencyInstance(Locale.US)
    Card(modifier = modifier){
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Surface(
                    modifier = Modifier.padding(5.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.errorContainer
                ) {
                    Text(
                        text= "Selling",
                        modifier = Modifier.padding(5.dp),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(
                    text= "Buying",
                    modifier = Modifier.weight(1f).padding(5.dp),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
            OutlinedCard (modifier = Modifier.padding(5.dp).align(androidx.compose.ui.Alignment.CenterHorizontally)){
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.padding(5.dp),
                    alignment = androidx.compose.ui.Alignment.Center
                )
            }
            Text(
                text = "Demon Shield",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = "World: Wintera",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = "2000000",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(5.dp)
            )
            Surface(
                modifier = Modifier.padding(5.dp),
                shape = MaterialTheme.shapes.medium,
                color = androidx.compose.ui.graphics.Color(Color.parseColor("#07bc0c"))
            ) {
                Text(
                    text= "Kalibra (Retro Hardcore PVP)",
                    modifier = Modifier.padding(5.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.align(androidx.compose.ui.Alignment.End)) {
                    Surface(
                        modifier = Modifier.width(20.dp),
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Text(
                            text= "1",
                            modifier = Modifier.padding(start=5.dp, end=5.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelSmall
                        )

                    }
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Filled.FavoriteBorder,
                        tint = MaterialTheme.colorScheme.error,
                        contentDescription = "Likes",
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, end = 5.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PeviewCard(){
    ItemTrade()
}