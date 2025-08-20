package com.miguel.tibiamerchants.presentation.fragments

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableSupportingPaneScaffold
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.presentation.Components.ErrorMessage
import com.miguel.tibiamerchants.presentation.Components.Loading
import com.miguel.tibiamerchants.presentation.Components.Toobar
import com.miguel.tibiamerchants.presentation.Components.Toolbar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelNPC
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelNPCS
import com.miguel.tibiamerchants.utils.utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import model.Tibia.ListNPC
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun NPCDefaultFragment(modifier: Modifier = Modifier, viewModel: ViewModelNPC = koinViewModel(), viewModelNPCS: ViewModelNPCS = koinViewModel()) {
    val scaffoldNavigator = rememberSupportingPaneScaffoldNavigator()
    val scope = rememberCoroutineScope()
    NavigableSupportingPaneScaffold(
        navigator = scaffoldNavigator,
        mainPane = {
            AnimatedPane(
                modifier = Modifier.fillMaxSize()
            ) {
                val npcs = utils().listNPC()
                Column (modifier = Modifier.fillMaxSize()) {
                    Toobar(stateAbout = viewModelNPCS)
                    GridLayoutNPC(npcs, scaffoldNavigator, scope, viewModel)
                }
            }
        },
        supportingPane = {
            AnimatedPane(modifier = Modifier.fillMaxSize()) {
                val npc = viewModel.npcInformation.collectAsStateWithLifecycle()
                Log.d("NPCFragment", "npc: ${npc.value}")
                when{
                    npc.value.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier.align(Alignment.Center)
                            ) {
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
                    npc.value.error != null -> {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ){
                            ErrorMessage(
                                messageHeader = "Error",
                                message = "An error occurred while loading data, please try again.",
                                modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                                onRetry = {
                                    scaffoldNavigator.currentDestination?.contentKey.let{
                                        viewModel.setNPCName(it.toString())
                                    }
                                }
                            )
                        }
                    }
                    npc.value.npc != null -> {
                        Column (
                            modifier = Modifier.fillMaxSize()
                        ){
                            //not expanded panel main
                            val isVisbleButton = scaffoldNavigator.
                            scaffoldValue[SupportingPaneScaffoldRole.Main] != PaneAdaptedValue.Expanded
                            Toolbar(title = npc.value.npc!!.body.nameNPC.toString(), onClick={
                                scope.launch {
                                    scaffoldNavigator.navigateTo(pane = SupportingPaneScaffoldRole.Main)
                                }
                            }, buttonVisible = isVisbleButton)
                            npc.value.npc?.body?.let{
                                NPCProfile(
                                    modifier = Modifier.fillMaxSize(),
                                    data = it
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun GridLayoutNPC(
    npcs: List<ListNPC>,
    scaffoldNavigator: ThreePaneScaffoldNavigator<Any>,
    scope: CoroutineScope = rememberCoroutineScope(),
    viewModel: ViewModelNPC
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(npcs.size) { npc ->
            CardNPC(npcs[npc], onClick = {
                viewModel.setNPCName(npcs[npc].name)
                scope.launch {
                    scaffoldNavigator.navigateTo(pane = SupportingPaneScaffoldRole.Supporting, contentKey = npcs[npc].name)
                }
            })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardNPC(npc: ListNPC, onClick: () -> Unit = {}) {
    Card(
        onClick = onClick,
        Modifier
            //.size(width = 80.dp, height = 80.dp)
            .padding(5.dp)
    )
    {
        Box(
            Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            GlideImage(
                model = npc.imgNPC,
                failure = placeholder(R.drawable.error_image_icon),
                modifier = Modifier
                    .size(width = 100.dp, height = 100.dp)
                    .align(Alignment.Center)
                    .padding(10.dp),
                contentDescription = "gif"
            )
        }
    }
}
