package com.mohanad.newsappkotlin.navigation

// The Navigation routes
sealed class NavRoute (val route:String){
    // SplashScreen Route
    data object SplashScreen: NavRoute("splash screen")
    // OnBoardingOneScreen Route
    data object OnBoardingOne: NavRoute("onboarding one")
    // OnBoardingTwoScreen Route
    data object OnBoardingTwo: NavRoute("onboarding two")
    // OnBoardingThreeScreen Route
    data object OnBoardingThree: NavRoute("onboarding three")
    // LoginScreen Route
    data object Login: NavRoute("login")
    // SignupScreen Route
    data object SignUp : NavRoute("sign up")
    // HomeScreen Route
    data object Home: NavRoute("home")
}