package com.miguel.tibiamerchants.presentation.Components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelItems
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelNPC
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelNPCS
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelSpells
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModeltemsType

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Toobar(stateAbout: ViewModelNPCS?) {
    val textStle = androidx.compose.ui.text.TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    Row(Modifier.fillMaxWidth(1f)) {
        Text(
            modifier = Modifier
                .padding(15.dp, 10.dp, 0.dp, 0.dp),
            text = "Tibia Merchants",
            color = MaterialTheme.colorScheme.secondary,
            style = textStle
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.End
        ) {
            DropDownMenu(null, stateAbout = stateAbout!!)
        }
    }
}

/**
 * @param tittle Title for NPCInfation view
 * @param viewmodel Viewmodel for NPCInfation view
 * Toolbar for items list view
 * **/
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ToobarNPC(tittle: String, viewmodel: ViewModelNPC?) {
    val textStle = androidx.compose.ui.text.TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    Row(Modifier.fillMaxWidth(1f)) {
        Backbutton(viewmodel = viewmodel!!)
        Text(
            modifier = Modifier
                .padding(5.dp, 10.dp, 0.dp, 0.dp),
            text = tittle,
            color = MaterialTheme.colorScheme.secondary,
            style = textStle
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.End
        ) {
            //DropDownMenu(viewmodel)
        }
    }
}

/**
 * @param tittle Title for items view
 * @param viewmodel Viewmodel for items view
 * Toolbar for items list view
 * **/
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Toolbar(tittle: String, viewmodel: ViewModelItems) {
    val textStle = androidx.compose.ui.text.TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    Row(Modifier.fillMaxWidth(1f)) {
        Backbutton(viewmodel = viewmodel)
        Text(
            modifier = Modifier
                .padding(5.dp, 10.dp, 0.dp, 0.dp),
            text = tittle,
            color = MaterialTheme.colorScheme.secondary,
            style = textStle
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.End
        ) {
            //DropDownMenu(viewmodel)
        }
    }
}

/**
 * @param tittle Title for items view type
 * @param viewmodel Viewmodel for items view type
 * Toolbar for items list type
 * **/
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Toolbar(tittle: String,  viewModel: ViewModeltemsType) {
    val textStle = androidx.compose.ui.text.TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    Row(Modifier.fillMaxWidth(1f)) {
        Backbutton(viewmodel = viewModel)
        Text(
            modifier = Modifier
                .padding(5.dp, 10.dp, 0.dp, 0.dp),
            text = tittle,
            color = MaterialTheme.colorScheme.secondary,
            style = textStle
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.End
        ) {
            //DropDownMenu(viewmodel)
        }
    }
}

@Composable
fun ToolBarSpells(tittle: String? = null, viewmodel: ViewModelSpells?= null){
    val textStle = androidx.compose.ui.text.TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    Row(Modifier.fillMaxWidth(1f)) {
        BackButtonSpells(Modifier, viewmodel)
        tittle?.let {
            Text(
                modifier = Modifier
                    .padding(5.dp, 10.dp, 0.dp, 0.dp),
                text = it,
                color = MaterialTheme.colorScheme.secondary,
                style = textStle
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.End
        ) {
            //DropDownMenu(viewmodel)
        }
    }
}

@Composable
fun ToolBarItemsProfile(tittle: String? = null, viewmodel: ViewModelSpells?= null){
    val textStle = androidx.compose.ui.text.TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    Row(Modifier.fillMaxWidth(1f)) {
        BackButtonItemProfile(Modifier)
        tittle?.let {
            Text(
                modifier = Modifier
                    .padding(5.dp, 10.dp, 0.dp, 0.dp),
                text = it,
                color = MaterialTheme.colorScheme.secondary,
                style = textStle
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.End
        ) {
            //DropDownMenu(viewmodel)
        }
    }
}

@Composable
fun BackButtonItemProfile(Modifier: Modifier, viewModel: ViewModelSpells? = null) {
    Box {
        IconButton(onClick = {
            viewModel?.setBack(true)
        }) {
            Icon(Icons.Default.KeyboardArrowLeft , contentDescription = "delete", modifier = Modifier.size(30.dp))
        }
    }
}

@Composable
fun BackButtonSpells(Modifier: Modifier, viewModel: ViewModelSpells? = null) {
    Box {
        IconButton(onClick = {
            viewModel?.setBack(true)
        }) {
            Icon(Icons.Default.KeyboardArrowLeft , contentDescription = "delete", modifier = Modifier.size(30.dp))
        }
    }
}


@Composable
fun Backbutton(viewmodel: ViewModelItems) {
    Box {
        IconButton(onClick = {
            viewmodel.setBack(true)
        }) {
            Icon(Icons.Default.KeyboardArrowLeft , contentDescription = "delete", modifier = Modifier.size(30.dp))
        }
    }
}

@Composable
fun Backbutton(viewmodel: ViewModelNPC?) {
    Box {
        IconButton(onClick = {
          viewmodel?.setBack(true)
        }) {
            Icon(Icons.Default.KeyboardArrowLeft , contentDescription = "delete", modifier = Modifier.size(30.dp))
        }
    }
}
@Composable
fun Backbutton(viewmodel:  ViewModeltemsType?) {
    Box {
        IconButton(onClick = {
            viewmodel?.setBack(true)
        }) {
            Icon(Icons.Default.KeyboardArrowLeft , contentDescription = "delete",modifier = Modifier.size(30.dp))
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun DropDownMenu(viewmodel: ViewModelNPC?, stateAbout: ViewModelNPCS) {
    var expanded by remember { mutableStateOf(false) }
    //val contextForToast = LocalContext.current.applicationContext
    Box {
        IconButton(onClick = { expanded = true}) {
            Icon(Icons.Default.MoreVert , contentDescription = "Open Menu")
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
//                DropdownMenuItem(text = {
//                    Text("Agregar nueva lista")
//                },
//                    onClick = {
//                        expanded = false
//                    })
                DropdownMenuItem(text = {
                    Text("About")
                },
                    onClick = {
                        stateAbout.setAboutState(true)
                        expanded = false
                    }
                )
                DropdownMenuItem(text = {
                    Text("Salir")
                },
                    onClick = {
                        expanded = false
                        viewmodel?.setBack(true)
                    }
                )
            }
        }
    }
}