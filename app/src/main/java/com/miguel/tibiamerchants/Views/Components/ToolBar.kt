package com.miguel.tibiamerchants.Views.Components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miguel.tibiamerchants.Views.ViewModels.ViewModelNPC
import com.miguel.tibiamerchants.Views.ViewModels.ViewModelNPCS

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

@Composable
fun Backbutton(viewmodel: ViewModelNPC) {
    Box {
        IconButton(onClick = {
          viewmodel.setBack(true)
        }) {
            Icon(Icons.Default.ArrowBack , contentDescription = "delete")
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