package com.mohanad.newsappkotlin.ui.view

import com.mohanad.newsappkotlin.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.mohanad.newsappkotlin.navigation.NavRoute
import com.mohanad.newsappkotlin.ui.theme.mainBlue
import com.mohanad.newsappkotlin.ui.theme.mainGrey
import com.mohanad.newsappkotlin.ui.view.composable.OnBoardingImage
import com.mohanad.newsappkotlin.ui.view.composable.OnBoardingNextButton
import com.mohanad.newsappkotlin.ui.view.composable.OnBoardingText
import com.mohanad.newsappkotlin.ui.view.composable.OnBoardingTitle
import com.mohanad.newsappkotlin.ui.view.composable.PointsRow

// Onboarding One Screen
@Composable
fun OnBoardingOneView(navController: NavHostController){
    ConstraintLayout (
        modifier = Modifier.fillMaxSize()
    ){
        val guideline = createGuidelineFromTop(0.6f)
        val guideline0 = createGuidelineFromTop(0.9f)

        val (image,txt1,txt2,txt3,txt4,dotsRow,button) = createRefs()

        OnBoardingImage(
            id = R.drawable.on_boarding_one,
            modifier = Modifier.constrainAs(image){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(guideline)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        )
        OnBoardingTitle(
            text = "Lorem Ipsum is simply",
            modifier = Modifier.constrainAs(txt1){
                top.linkTo(guideline, margin = 20.dp)
                start.linkTo(parent.start, margin = 20.dp)
            }
        )
        OnBoardingTitle(
            text = "dummy",
            modifier = Modifier.constrainAs(txt2){
                top.linkTo(txt1.bottom, margin = 5.dp)
                start.linkTo(txt1.start)
            }
        )
        OnBoardingText(
            text = "Lorem Ipsum is simply dummy text of",
            modifier =  Modifier.constrainAs(txt3){
                top.linkTo(txt2.bottom, margin = 10.dp)
                start.linkTo(txt1.start)
            }
        )
        OnBoardingText(
            text = "the printing and typesetting industry.",
            modifier = Modifier.constrainAs(txt4){
                top.linkTo(txt3.bottom, margin = 5.dp)
                start.linkTo(txt1.start)
            }
        )
        PointsRow(
            firstColor = mainBlue,
            secondColor = mainGrey,
            thirdColor = mainGrey,
            modifier = Modifier.constrainAs(dotsRow){
                top.linkTo(guideline0)
                start.linkTo(txt1.start)
            }
        )
        OnBoardingNextButton(
            text = "Next",
            modifier = Modifier.constrainAs(button){
            top.linkTo(dotsRow.top)
            bottom.linkTo(dotsRow.bottom)
            end.linkTo(parent.end,20.dp)
        }) {
            navController.navigate(NavRoute.OnBoardingTwo.route)
        }
    }
}
