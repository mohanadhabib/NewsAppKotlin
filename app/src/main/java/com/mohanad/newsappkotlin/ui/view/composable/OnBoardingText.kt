package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// The main text in onboarding screens
@Composable
fun OnBoardingText(text:String , modifier: Modifier){
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Gray,
        modifier = modifier
    )
}