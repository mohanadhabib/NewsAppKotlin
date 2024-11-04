package com.mohanad.newsappkotlin.ui.view

import com.mohanad.newsappkotlin.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mohanad.newsappkotlin.navigation.NavRoute
import com.mohanad.newsappkotlin.ui.viewmodel.LoginViewModel
import com.mohanad.newsappkotlin.ui.viewmodel.SignUpViewModel
import kotlinx.coroutines.delay

// Splash Screen
@Composable
fun SplashScreenView(navController: NavHostController , signUpViewModel: SignUpViewModel , loginViewModel: LoginViewModel) {

    val userIdSignUp by signUpViewModel.storedId.observeAsState()

    val userIdLogIn by loginViewModel.storedId.observeAsState()

    val currentId = Firebase.auth.uid

    val nextDestination = if(currentId == userIdSignUp && currentId == userIdLogIn) NavRoute.Home.route else NavRoute.OnBoardingOne.route

    ConstraintLayout (
        modifier = Modifier.fillMaxSize()
    ){
        val logo = createRef()
        val guideLine = createGuidelineFromTop(0.3f)

        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "App Logo",
            modifier = Modifier.constrainAs(logo){
                top.linkTo(guideLine)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
    LaunchedEffect(Unit) {
        delay(1000)
        navController.navigate(nextDestination){
            popUpTo (NavRoute.SplashScreen.route){
                inclusive = true
            }
        }
    }
}