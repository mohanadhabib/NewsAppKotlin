package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohanad.newsappkotlin.ui.theme.mainGrey
import com.mohanad.newsappkotlin.ui.theme.mainGreyContainer

@Composable
fun SocialMediaButton(text:String , contentDescription: String , modifier: Modifier , onClick:()->Unit , painter: Painter){
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = mainGreyContainer
        ),

        onClick = onClick
    ) {
        Image(painter = painter , contentDescription = contentDescription)
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = mainGrey,
            modifier = modifier.padding(10.dp)
            )
    }
}