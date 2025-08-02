package com.miguel.tibiamerchants.presentation.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.miguel.tibiamerchants.R
import com.miguel.tibiamerchants.presentation.Components.Toobar
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelNPCS
import com.miguel.tibiamerchants.utils.utils
import model.Tibia.NPCModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NPCDefaultFragment(modifier: Modifier = Modifier, viewModel: ViewModelNPCS = koinViewModel()) {
    val npcs = utils().listNPC()
    Column {
        Toobar(stateAbout = viewModel)
        GridLayoutNPC(npcs, viewModel)
    }
}

@Composable
fun GridLayoutNPC(npcs: List<NPCModel>, viewModel: ViewModelNPCS?) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(npcs.size){npc->
            CardNPC(npcs[npc], viewModel!!)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardNPC(npc: NPCModel, viewModel: ViewModelNPCS) {
    Card(
        onClick = {
            viewModel.setNPCName(npc.nameNPC.toString())
        },
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
