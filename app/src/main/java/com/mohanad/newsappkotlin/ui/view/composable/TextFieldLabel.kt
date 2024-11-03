package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.mohanad.newsappkotlin.ui.theme.mainBlackGrey
import com.mohanad.newsappkotlin.ui.theme.mainRedError

@Composable
fun TextFieldLabel(text:String , modifier: Modifier){
    Row (
        modifier = modifier.fillMaxWidth()
    ){
        Text(
            text = text,
            fontSize = 18.sp,
            color = mainBlackGrey,
            modifier = modifier
        )
        Text(
            text = "*",
            fontSize = 18.sp,
            color = mainRedError,
            modifier = modifier
        )
    }
}