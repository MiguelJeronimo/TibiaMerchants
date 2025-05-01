package com.miguel.tibiamerchants.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miguel.tibiamerchants.domain.models.navigation.VocationsRouters
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme


class Vocations : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TibiaMerchantsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    // Get current back stack entry
                    val backStackEntry = navController.currentBackStackEntryAsState()
                    NavHost(
                        navController = navController,
                        startDestination = VocationsRouters.Vocations.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(VocationsRouters.Vocations.name) {
                            Vocation(
                                navController = navController
                            )
                        }
                        composable(VocationsRouters.Vocation.name) {
                            Greeting3("Android", navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Vocation(
    navController: NavHostController,
){
    Button(onClick = {
        navController.navigate(VocationsRouters.Vocation.name)
    }) {
        Text("Mensaje")
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier, navController: NavHostController?) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = {
            navController?.popBackStack(
                VocationsRouters.Vocations.name,
                inclusive = false
            )
        }){
            Text("Regresar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    TibiaMerchantsTheme {
        Greeting3("Android", navController = null)
    }
}