package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// The title text in signup and login screens
@Composable
fun WelcomeText(text:String ,color:Color ,modifier: Modifier){
    Text(
        text = text,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        modifier = modifier
    )
}