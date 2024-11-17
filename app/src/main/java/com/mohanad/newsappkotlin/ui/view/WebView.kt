package com.mohanad.newsappkotlin.ui.view

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebView(url:String){
       val context = LocalContext.current
       AndroidView(
           modifier = Modifier.fillMaxSize(),
           factory = {
               val webView = WebView(context)
               webView.loadUrl(url)
               webView
           }
       )
}