package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mohanad.newsappkotlin.R

// Back arrow in some screens like selecting country
@Composable
fun BackArrow(onClick:()->Unit ,modifier: Modifier) {
    Image(
        modifier = modifier.clip(RoundedCornerShape(20.dp)).clickable{onClick()},
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = "Back Button"
    )
}