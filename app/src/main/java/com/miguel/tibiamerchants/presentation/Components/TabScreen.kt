package com.miguel.tibiamerchants.presentation.Components

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TabScreen(modifier: Modifier) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Npc Default", "Tc to Gold", "Tibia Trade")
    Column(modifier = modifier) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        when (index) {
                            0 -> {
                                Icon(
                                    painter = painterResource(id = com.miguel.tibiamerchants.R.drawable.rashid),
                                    contentDescription = null
                                )
                            }
                            1 -> {
                                Icon(
                                    painter = painterResource(id = com.miguel.tibiamerchants.R.drawable.tibia_coins_escapet_150x150),
                                    contentDescription = null
                                )
                            }
                            2 -> {
                                Icon(
                                    painter = painterResource(id = com.miguel.tibiamerchants.R.drawable.trade_icon_png),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabPreview(){
    TabScreen(modifier = Modifier.fillMaxWidth())
}