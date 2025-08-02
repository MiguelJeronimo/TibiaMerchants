package com.miguel.tibiamerchants.presentation.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelVocations
import org.koin.androidx.compose.koinViewModel

//, viewModel: ViewModelVocations = koinViewModel()
@Composable
fun VocationsItem(
    modifier: Modifier = Modifier,
    name: String = "Paladin",
    description: String = "Masters of distance fighting with bows and crossbows and average magic users.",
    onClick: () -> Unit = {}
){
    OutlinedCard(
        modifier = modifier,
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)){
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = description,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VocationsPreview(){
    VocationsItem(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(5.dp)
    )
}