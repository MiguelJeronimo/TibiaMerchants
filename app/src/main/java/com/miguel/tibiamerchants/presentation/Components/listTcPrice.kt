package com.miguel.tibiamerchants.presentation.Components

import androidx.compose.foundation.border
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListTcPrice(){
    LazyColumn() {
        items(10){
            ItemListTC(modifier = Modifier
                .fillMaxWidth())
            HorizontalDivider(
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
fun ItemListTC(modifier: Modifier){
    Surface (modifier = modifier){
        Column() {
            Row {
                Text(
                    text = "World",
                    modifier = Modifier.weight(0.5f).padding(5.dp),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Created: 2025-07-25T10:52:03.342Z",
                    modifier = Modifier.weight(1f).padding(5.dp),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.End
                )
            }
            Text(
                "Wintera",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 5.dp)
            )
            Text(
                text = "Buy average price: 38860",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 5.dp)
            )
            Text(
                text = "Buy highest price: 40700",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 5.dp)
            )
            HorizontalDivider(
                modifier = Modifier.padding(5.dp).border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = "Sell lowest price: 37605",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 5.dp)
            )
            Text(
                text = "Sell average price: 40419",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    ListTcPrice()
//    ItemListTC(
//        modifier = Modifier
//            .fillMaxWidth()
//    )
}