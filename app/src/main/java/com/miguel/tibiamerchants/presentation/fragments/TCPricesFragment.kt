package com.miguel.tibiamerchants.presentation.fragments

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.miguel.tibiamerchants.presentation.Components.ErrorComponent
import com.miguel.tibiamerchants.presentation.Components.ListTcPrice
import com.miguel.tibiamerchants.presentation.Components.Loading
import com.miguel.tibiamerchants.presentation.Components.Toobar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelTibiaTrade
import org.koin.androidx.compose.koinViewModel


@Composable
fun TcPriceFragment(
    navController: NavController,
    viewModel: ViewModelTibiaTrade = koinViewModel()
) {
    val state by viewModel.tcPrice.collectAsState()
    TCPriceFragment(state)
}

@Composable
fun TCPriceFragment(
    state: ViewModelTibiaTrade.UIState
){
    var loadingState by rememberSaveable { mutableStateOf(true) }
    Log.d("state", state.toString())
    Column {
        Toobar(title = "Tibia Coin Price")
        when{
            state.isLoding -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ){
                        Loading(
                            modifier = Modifier
                                .width(64.dp).align(Alignment.CenterHorizontally).padding(5.dp)
                        )
                        Text(
                            text = "Loading...",
                            modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp)
                        )
                    }
                }
            }
            state.data != null -> {
                loadingState = false
                ListTcPrice(state = state.data)
            }
            state.error != null -> {
                loadingState = false
                Log.d("error", state.error)
                ErrorComponent(message = state.error)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewLoading(){
    val state = ViewModelTibiaTrade.UIState(isLoding = true)
    TCPriceFragment(state = state)
}