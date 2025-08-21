package com.miguel.tibiamerchants.presentation.Components

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled", "ObsoleteSdkInt")
@Composable
fun TibiaMapsComponent(modifier: Modifier = Modifier, url: String, activeJs: Boolean = true){
    Log.d("Tibia Maps", url)
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                // Habilitar estas configuraciones para mejorar la compatibilidad y el rendimiento.
                settings.domStorageEnabled = true // Permite el almacenamiento local de la página.
                settings.loadWithOverviewMode = true // Carga la página completa en el WebView.
                settings.useWideViewPort = true // Habilita la vista de ventana ancha.
                settings.allowContentAccess = true // Permite el acceso a archivos desde el contenido.
                settings.allowFileAccess = true // Permite que la página acceda a archivos locales.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
                // Aquí se agrega el WebViewClient
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        },
        update = { webView ->
            // Si la URL cambia, la actualiza
            webView.loadUrl(url)
        }
    )
}