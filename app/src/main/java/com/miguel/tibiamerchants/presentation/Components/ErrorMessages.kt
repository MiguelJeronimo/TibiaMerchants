package com.miguel.tibiamerchants.presentation.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessage(messageHeader: String = "Error", message: String = "Something went wrong", modifier:Modifier = Modifier, onRetry: () -> Unit = {}){
    Column (modifier = modifier){
        Icon(
            imageVector = androidx.compose.material.icons.Icons.Filled.Warning,
            contentDescription = "Warning",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = messageHeader,
            modifier = Modifier.padding(bottom = 5.dp).align(Alignment.CenterHorizontally),
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
            color = androidx.compose.material3.MaterialTheme.colorScheme.error
        )
        Text(
            text = message,
            modifier = Modifier.align(Alignment.CenterHorizontally)
            .padding(bottom = 5.dp),
            color = androidx.compose.material3.MaterialTheme.colorScheme.error,
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}