package com.mohanad.newsappkotlin.ui.view.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

// The image in the onboarding screens
@Composable
fun OnBoardingImage(@DrawableRes id:Int, modifier: Modifier) {
    Image(
        painter = painterResource(id),
        contentDescription = "OnBoardingOneImage",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
    )
}