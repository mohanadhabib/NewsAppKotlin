package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mohanad.newsappkotlin.ui.theme.mainGrey

// The label of news category and see all button together
@Composable
fun MainRow(modifier: Modifier , text:String , seeAllButton:Boolean , onClick:()->Unit ){
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = text,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        if(seeAllButton){
            Text(
                modifier = Modifier.clickable { onClick() },
                text = "See all",
                color = mainGrey,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}