package com.mohanad.newsappkotlin.navigation

sealed class NavRoute (val route:String){
    data object SplashScreen: NavRoute("splash screen")
    data object OnBoardingOne: NavRoute("onboarding one")
    data object OnBoardingTwo: NavRoute("onboarding two")
    data object OnBoardingThree: NavRoute("onboarding three")
    data object Login: NavRoute("login")
    data object SignUp : NavRoute("sign up")
    data object Home: NavRoute("home")
}