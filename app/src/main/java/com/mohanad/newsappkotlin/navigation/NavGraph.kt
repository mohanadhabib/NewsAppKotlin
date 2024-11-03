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

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = NavRoute.SplashScreen.route){
        composable(route = NavRoute.SplashScreen.route){
            val signUpViewModel = viewModel(modelClass = SignUpViewModel::class)
            val loginViewModel = viewModel(modelClass = LoginViewModel::class)

            SplashScreenView(
                navController = navController,
                signUpViewModel = signUpViewModel,
                loginViewModel = loginViewModel
                )
        }
        composable(route = NavRoute.OnBoardingOne.route){
            OnBoardingOneView(navController = navController)
        }
        composable(route = NavRoute.OnBoardingTwo.route){
            OnBoardingTwoView(navController = navController)
        }
        composable(route = NavRoute.OnBoardingThree.route){
            OnBoardingThreeView(navController = navController)
        }
        composable(route = NavRoute.Login.route){
            val viewModel = viewModel (LoginViewModel::class)
            LoginView(
                viewModel = viewModel,
                navController = navController)
        }
        composable(route = NavRoute.SignUp.route){
            val viewModel = viewModel (SignUpViewModel::class)
            SignUpView(
                viewModel = viewModel,
                navController = navController)
        }
        composable(route = NavRoute.Home.route){
            HomeView()
        }
    }
}