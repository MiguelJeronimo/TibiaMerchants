package com.miguel.tibiamerchants.presentation.fragments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.miguel.tibiamerchants.domain.models.tibiatrade.UserAdsActive
import com.miguel.tibiamerchants.presentation.Components.ErrorMessage
import com.miguel.tibiamerchants.presentation.Components.Loading
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelTibiaTrade
import org.koin.androidx.compose.koinViewModel

@Composable
fun TibiaTradeProfile(
    toolBarTitle: String,
    modifier: Modifier = Modifier,
    viewModel: ViewModelTibiaTrade = koinViewModel(),
    navigate: NavHostController
){
    val state = viewModel.profile.collectAsStateWithLifecycle()
    when{
        state.value.isLoding -> {
            Box (
                modifier = modifier
            ){
                Column (
                    modifier = Modifier.align(Alignment.Center)
                ){
                    Loading(modifier = Modifier.size(64.dp))
                    Text(
                        text = "Loading...",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        state.value.error != null -> {
            ErrorMessage(
                message = state.value.error.toString(),
                modifier = modifier,
                onRetry = { viewModel.profile(toolBarTitle) }
            )
        }
        state.value.data != null -> {
            TibiaTradeModel(
                toolBarTitle = toolBarTitle,
                modifier = modifier,
                state = state.value,
                navigate = navigate
            )
        }
    }
}

@Composable
fun TibiaTradeModel(
    toolBarTitle: String,
    modifier: Modifier = Modifier,
    state: ViewModelTibiaTrade.UIStateProfile = ViewModelTibiaTrade.UIStateProfile(),
    navigate: NavHostController = NavHostController(LocalContext.current) ,
){
    UserAdsActive(
        modifier = modifier,
        tibia = state.data,
        navigate = navigate
    )
}