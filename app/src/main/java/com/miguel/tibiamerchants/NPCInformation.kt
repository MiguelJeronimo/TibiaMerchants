package com.miguel.tibiamerchants

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguel.tibiamerchants.Views.Components.Toobar
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme

class NPCInformation : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TibiaMerchantsTheme {
                val nameNPC = intent.extras?.getString("npc")
                var stateName by rememberSaveable { mutableStateOf("") }
                stateName = nameNPC.toString()
                println(stateName)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Toobar()
                        CardDescription()
                    }
                }
            }
        }
    }
}

@Composable
fun CardDescription(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            //imagen del npc
            //imagen de la ubicacion
            Text(text = "Notes He's the leader and recruiter of the Paw and Fur Society. Main NPC of the Killing in the Name of... Quest.")
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedCard {
                Column(Modifier.padding(16.dp)) {
                    Text(text = "Nearest City: Port Hope")
                    Text(text = "Gender: Male")
                    Text(text = "Race: Human")
                    Text(text = "Job: Hunter Organisation Leader")
                    Text(text = "Version: 8.50 July 1, 2009 Summer Update 2009")
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Status: Active", Modifier.align(Alignment.End))
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TibiaMerchantsTheme {
        Column {
          CardDescription()
        }
    }
}