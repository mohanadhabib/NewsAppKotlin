package com.mohanad.newsappkotlin.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohanad.newsappkotlin.ui.view.HomeView
import com.mohanad.newsappkotlin.ui.view.LoginView
import com.mohanad.newsappkotlin.ui.view.OnBoardingOneView
import com.mohanad.newsappkotlin.ui.view.OnBoardingThreeView
import com.mohanad.newsappkotlin.ui.view.OnBoardingTwoView
import com.mohanad.newsappkotlin.ui.view.SignUpView
import com.mohanad.newsappkotlin.ui.view.SplashScreenView
import com.mohanad.newsappkotlin.ui.viewmodel.LoginViewModel
import com.mohanad.newsappkotlin.ui.viewmodel.SignUpViewModel

// The app Navigation graph
@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = NavRoute.SplashScreen.route){
        // SplashScreen
        composable(route = NavRoute.SplashScreen.route){
            val signUpViewModel = viewModel(modelClass = SignUpViewModel::class)
            val loginViewModel = viewModel(modelClass = LoginViewModel::class)

            SplashScreenView(
                navController = navController,
                signUpViewModel = signUpViewModel,
                loginViewModel = loginViewModel
                )
        }
        // OnboardingOneScreen
        composable(route = NavRoute.OnBoardingOne.route){
            OnBoardingOneView(navController = navController)
        }
        // OnboardingTwoScreen
        composable(route = NavRoute.OnBoardingTwo.route){
            OnBoardingTwoView(navController = navController)
        }
        // OnboardingThreeScreen
        composable(route = NavRoute.OnBoardingThree.route){
            OnBoardingThreeView(navController = navController)
        }
        // LoginScreen
        composable(route = NavRoute.Login.route){
            val viewModel = viewModel (LoginViewModel::class)
            LoginView(
                viewModel = viewModel,
                navController = navController)
        }
        // SignupScreen
        composable(route = NavRoute.SignUp.route){
            val viewModel = viewModel (SignUpViewModel::class)
            SignUpView(
                viewModel = viewModel,
                navController = navController)
        }
        // HomeScreen
        composable(route = NavRoute.Home.route){
            HomeView()
        }
    }
}