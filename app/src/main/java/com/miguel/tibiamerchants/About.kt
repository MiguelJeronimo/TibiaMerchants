package com.miguel.tibiamerchants

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.Views.Components.ToobarNPC
import com.miguel.tibiamerchants.Views.ViewModels.ViewModelNPC
import com.miguel.tibiamerchants.ui.theme.TibiaMerchantsTheme

class About : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel = ViewModelProvider(this)[ViewModelNPC::class.java]
        enableEdgeToEdge()
        setContent {
            TibiaMerchantsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    Column(Modifier.padding(padding)) {
                        ToobarNPC(tittle = "About", viewmodel = viewmodel)
                        ContentAbout()
                    }
                }
            }
        }
        viewmodel.isBack.observe(this, Observer {
            if (it) {
                finish()
            }
        })
    }
}

@Composable
fun ContentAbout() {
    val context = LocalContext.current
    val linkflytext = remember { TextView(context) }
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            Modifier
                .height(520.dp)
                .wrapContentHeight(align = Alignment.Top)
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Tibia Merchants",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "It is a query tool for Tibia Online game, to get information about NPCs",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 10.dp, 0.dp, 10.dp),
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Tibia Online is owned by CipSoft",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 10.dp, 0.dp, 0.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            AndroidView(
                factory = { linkflytext },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) { textView ->
                textView.text = "Web site game: https://tibia.com/"
                Linkify.addLinks(textView, Linkify.WEB_URLS)
                textView.movementMethod = LinkMovementMethod.getInstance()
            }
            Text(
                text = "Created by Miguel Jerónimo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 10.dp, 0.dp, 10.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "You can contact me in game as: Selur Zaole \uD83D\uDE0E",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Text(
                text = "You can make a donation, buy my app, buy me a coffe, or send TCs.  \uD83E\uDD13",
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(5.dp),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)) {

                Image(
                    painter = painterResource(id = R.drawable.swift_shop_logo),
                    contentDescription = "github",
                    Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .padding(5.dp)
                        .clickable {
                            val url = "https://play.google.com/store/apps/details?id=com.miguel.shoppinglistpro"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.mi_kofi),
                    contentDescription = "github",
                    Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .padding(5.dp)
                        .clickable {
                            val url = "https://ko-fi.com/selursan"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.github),
                    contentDescription = "github",
                    Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .padding(5.dp)
                        .clickable {
                            val url = "https://github.com/MiguelJeronimo/TibiaMerchants"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        },
                )
            }
        }

        Column(
            Modifier
                .padding(5.dp)
                .wrapContentHeight(align = Alignment.Bottom)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Version: 1.0",
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "License open source: MIT",
                Modifier
                    .padding(0.dp, 0.dp, 0.dp, 16.dp)
                    .align(Alignment.CenterHorizontally),
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    TibiaMerchantsTheme {
        ContentAbout()
    }
}