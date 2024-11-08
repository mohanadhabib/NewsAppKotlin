package com.mohanad.newsappkotlin.ui.view.composable

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SetupScreensTemplate(navController:NavHostController, @SuppressLint("ModifierParameter") arrowModifier: Modifier, labelModifier: Modifier, buttonModifier: Modifier, labelTxt:String, onClick:()->Unit){
    BackArrow(
        onClick = {
            navController.popBackStack()
        },
        modifier = arrowModifier)

    LabelText(
        text = labelTxt,
        modifier = labelModifier)

    OnBoardingNextButton(
        text = "Next",
        modifier = buttonModifier) {
        onClick()
    }
}