package com.mohanad.newsappkotlin.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.mohanad.newsappkotlin.data.model.News
import com.mohanad.newsappkotlin.ui.view.BottomBookmarkView
import com.mohanad.newsappkotlin.ui.view.BottomExploreView
import com.mohanad.newsappkotlin.ui.view.BottomHomeView
import com.mohanad.newsappkotlin.ui.view.BottomProfileView
import com.mohanad.newsappkotlin.ui.view.DetailsView
import com.mohanad.newsappkotlin.ui.view.EditProfileView
import com.mohanad.newsappkotlin.ui.view.LatestNewsView
import com.mohanad.newsappkotlin.ui.view.TrendingNewsView
import com.mohanad.newsappkotlin.ui.view.WebView
import com.mohanad.newsappkotlin.ui.viewmodel.BottomBookmarkViewModel
import com.mohanad.newsappkotlin.ui.viewmodel.BottomExploreViewModel
import com.mohanad.newsappkotlin.ui.viewmodel.BottomHomeViewModel
import com.mohanad.newsappkotlin.ui.viewmodel.BottomProfileViewModel
import com.mohanad.newsappkotlin.ui.viewmodel.EditProfileViewModel

// The bottom taps and additional screens Navigation graph
@Composable
fun BottomNavGraph(mainNavController:NavHostController , navController: NavHostController){
    NavHost(navController = navController, startDestination = BottomNavRoute.HomeNavItem.route) {
        // BottomHomeScreen
        composable(route = BottomNavRoute.HomeNavItem.route) {
            val viewModel = viewModel(modelClass = BottomHomeViewModel::class)
            BottomHomeView(viewModel = viewModel , navController = navController)
        }
        // BottomExploreScreen
        composable(route = BottomNavRoute.ExploreNavItem.route) {
            val viewModel = viewModel(modelClass = BottomExploreViewModel::class)
            BottomExploreView(viewModel = viewModel,navController = navController)
        }
        // BottomBookmarkScreen
        composable(route = BottomNavRoute.BookmarkNavItem.route) {
            val viewModel = viewModel(modelClass = BottomBookmarkViewModel::class)
            BottomBookmarkView(viewModel = viewModel , navController = navController)
        }
        // BottomProfileScreen
        composable(route = BottomNavRoute.ProfileNavItem.route) {
            val viewModel = viewModel(modelClass = BottomProfileViewModel::class)
            BottomProfileView(viewModel = viewModel , mainNavController = mainNavController , navController = navController)
        }
        // Additional Screens

        // LatestNewsScreen
        composable(route = BottomNavRoute.HomeLatestNews.route ){
            val viewModel = viewModel(modelClass = BottomHomeViewModel::class)
            LatestNewsView(viewModel = viewModel , navController = navController)
        }
        // TrendingNewsScreen
        composable(route = BottomNavRoute.HomeTrendingNews.route ){
            val viewModel = viewModel(modelClass = BottomHomeViewModel::class)
            TrendingNewsView(navController = navController,viewModel = viewModel)
        }
        // ArticleDetailsScreen
        composable(route = BottomNavRoute.DetailsView.route ){
            val gson = Gson()
            val timeTxt = navController.previousBackStackEntry?.savedStateHandle?.get<String>("time") ?: ""
            val newsTxt = navController.previousBackStackEntry?.savedStateHandle?.get<String>("news") ?: ""
            gson.fromJson(newsTxt,News::class.java)?.let { DetailsView(navController = navController, news = it , time = timeTxt) }
        }
        // EditProfileScreen
        composable(route = BottomNavRoute.EditProfile.route){
            val viewModel = viewModel(modelClass = EditProfileViewModel::class)
            EditProfileView(viewModel = viewModel, navController = navController)
        }
        //WebViewScreen
        composable(route = BottomNavRoute.WebView.route) {
            val url = navController.previousBackStackEntry?.savedStateHandle?.get<String>("url") ?: ""
            WebView(url = url)
        }
    }
}
