package com.miguel.tibiamerchants.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miguel.tibiamerchants.domain.models.navigation.VocationsRouters
import com.miguel.tibiamerchants.domain.models.vocations.VocationItems
import com.miguel.tibiamerchants.domain.models.vocations.VocationList
import com.miguel.tibiamerchants.presentation.Components.ToolBarVocation
import com.miguel.tibiamerchants.presentation.Components.VocationsItem
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
                            val arrayVocations = arrayListOf(
                                VocationList(
                                    name = "Paladin",
                                    description = "Masters of distance fighting with bows and crossbows and average magic users.",
                                ),
                                VocationList(
                                    name = "Knight",
                                    description = "Masters of distance fighting with bows and crossbows and average magic users.",
                                ),
                                VocationList(
                                    name = "Sorcerer",
                                    description = "Masters of distance fighting with bows and crossbows and average magic users.",
                                ),
                                VocationList(
                                    name = "Druid",
                                    description = "Masters of distance fighting with bows and crossbows and average magic users.",
                                ),
                            )
                            Vocation(
                                modifier = Modifier.fillMaxSize(),
                                vocationList = arrayVocations,
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
    navController: NavHostController? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    vocationList: ArrayList<VocationList>
){
    //navController.navigate(VocationsRouters.Vocation.name)
    Column(modifier = modifier) {
        ToolBarVocation("Vocations")
        LazyColumn {
            items(vocationList.size){
                VocationsItem(
                    modifier = Modifier.padding(5.dp).fillMaxWidth(),
                    name = vocationList[it].name,
                    description = vocationList[it].description,
                    onClick = {
                        navController?.navigate(VocationsRouters.Vocation.name)
                    }
                )
            }
        }
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
    val arrayVocations = arrayListOf(
        VocationList(
            name = "Paladin",
            description = "Masters of distance fighting with bows and crossbows and average magic users.",
        ),
        VocationList(
            name = "Knight",
            description = "Masters of distance fighting with bows and crossbows and average magic users.",
        ),
        VocationList(
            name = "Sorcerer",
            description = "Masters of distance fighting with bows and crossbows and average magic users.",
        ),
        VocationList(
            name = "Druid",
            description = "Masters of distance fighting with bows and crossbows and average magic users.",
        ),
    )
    TibiaMerchantsTheme {
        Vocation(
            modifier = Modifier.fillMaxSize(),
            vocationList = arrayVocations
        )
    }
}