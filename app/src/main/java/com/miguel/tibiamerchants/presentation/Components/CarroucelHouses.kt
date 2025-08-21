package com.miguel.tibiamerchants.presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.miguel.tibiamerchants.domain.models.TibiaTradeItemModel
import com.miguel.tibiamerchants.utils.Carroucel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarroucelHouse(modifier: Modifier = Modifier, data: TibiaTradeItemModel?){
    val houses = Carroucel().generateFloorImage(
        screenshotCount = data?.screenshotCount!!,
        tibiaId = data.ad.tibiaId!!
    ).floor()
    val state = rememberCarouselState { houses.size }
    val animationScope = rememberCoroutineScope()
    HorizontalUncontainedCarousel(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 16.dp),
        itemWidth = 250.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ){
        val item = houses[it]
        Box(
            modifier = Modifier
                .size(250.dp)
                .maskClip(MaterialTheme.shapes.extraLarge)
        ) {
            AsyncImage(
                model = item,
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Imagen de casa piso $it",
                contentScale = ContentScale.Crop
            )

            // 🔹 Leyenda del piso
            Text(
                text = "Floor: ${it+1}",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color.Black.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                    )
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCarroucel(){
    MaterialTheme {
        Scaffold {innerPadding ->
            CarroucelHouse(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(innerPadding),
                data = null
            )
        }
    }
}