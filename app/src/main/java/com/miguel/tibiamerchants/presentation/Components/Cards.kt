package com.miguel.tibiamerchants.presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme

@Composable
fun CardHeaderItemInfo() {
    Box(Modifier.fillMaxWidth().padding(10.dp)) {
        Card (
            modifier = Modifier.padding(start = 35.dp)
        ){
            Column(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(5.dp),
            ) {
                Text(
                    text = "Demon Shield",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Level: 400",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 3.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Vocation: Paladin",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 3.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Clasification: Body Equipment",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 3.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.swift_shop_logo),
            contentDescription = "demon logo",
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(80.dp)
                .clip(CircleShape)
                .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Observar() {
    TibiaMerchantsTheme {
        CardHeaderItemInfo()
    }
}