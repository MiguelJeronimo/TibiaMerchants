package com.miguel.tibiamerchants.presentation.fragments

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.miguel.tibiamerchants.presentation.Components.ListTcPrice
import com.miguel.tibiamerchants.presentation.Components.Toobar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelTibiaTrade
import org.koin.androidx.compose.koinViewModel

@Composable
fun TCPriceFragment(
    navController: NavController,
    viewModel: ViewModelTibiaTrade = koinViewModel()
){
    viewModel.tcPrice()
    val state = viewModel.tcPrice.collectAsState()
    Log.d("state", state.value.toString())
    Column {
        Toobar(title = "Tibia Coin Price")
        if (state.value != null){
            ListTcPrice(state)
        }
    }
}