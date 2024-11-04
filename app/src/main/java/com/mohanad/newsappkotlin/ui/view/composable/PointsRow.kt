package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mohanad.newsappkotlin.R

// The row that indicate the current onboarding screen
@Composable
fun PointsRow(firstColor: Color, secondColor:Color, thirdColor: Color, modifier: Modifier){
    Row (
        modifier = modifier.wrapContentWidth()
    ){
        Image(
            painter = painterResource(R.drawable.ic_point),
            contentDescription = "Point",
            colorFilter = ColorFilter.tint(firstColor)
        )
        Spacer(modifier = modifier.width(5.dp))
        Image(
            painter = painterResource(R.drawable.ic_point),
            contentDescription = "Point",
            colorFilter = ColorFilter.tint(secondColor)
        )
        Spacer(modifier = modifier.width(5.dp))
        Image(
            painter = painterResource(R.drawable.ic_point),
            contentDescription = "Point",
            colorFilter = ColorFilter.tint(thirdColor)
        )
    }
}