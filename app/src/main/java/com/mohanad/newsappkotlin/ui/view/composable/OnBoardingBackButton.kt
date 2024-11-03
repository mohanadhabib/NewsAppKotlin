package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnBoardingBackButton(modifier: Modifier, onClick:()-> Unit){
    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(
            text = "Back",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
        )
    }
}