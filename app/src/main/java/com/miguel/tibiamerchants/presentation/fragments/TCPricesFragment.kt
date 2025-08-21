package com.miguel.tibiamerchants.presentation.fragments

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.miguel.tibiamerchants.domain.models.PriceTcModel
import com.miguel.tibiamerchants.presentation.Components.ErrorComponent
import com.miguel.tibiamerchants.presentation.Components.ErrorMessage
import com.miguel.tibiamerchants.presentation.Components.ListTcPrice
import com.miguel.tibiamerchants.presentation.Components.Loading
import com.miguel.tibiamerchants.presentation.Components.Toobar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelTCPrice
import org.koin.androidx.compose.koinViewModel


@Composable
fun TcPriceFragment(
    navController: NavController,
    viewModel: ViewModelTCPrice = koinViewModel()
) {
    val state by viewModel.tcPrice.collectAsState()
    TCPriceFragment(state, onRetry = { viewModel.getTCPrice() })
}

@Composable
fun TCPriceFragment(
    state: ViewModelTCPrice.UIState,
    viewModel: ViewModelTCPrice = koinViewModel(),
    onRetry: () -> Unit = {},
){
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
            state.data != null -> {
                val textState = rememberSaveable { mutableStateOf("") }
                TextField(
                    value = textState.value,
                    onValueChange = {
                        textState.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = CircleShape,
                    keyboardActions = KeyboardActions {
                        viewModel.searchWorld(textState.value)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    },
                    placeholder = { Text("Search by world") },
                    singleLine = true,
                    colors = androidx.compose.material3.TextFieldDefaults.colors(
                        focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                        unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                        disabledIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    )
                )
                ListTcPrice(state = state.data)
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewLoading(){
    val state = ViewModelTCPrice.UIState(isLoding = true)
    TCPriceFragment(state = state)
}