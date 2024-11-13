package com.mohanad.newsappkotlin.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohanad.newsappkotlin.ui.view.BottomHomeView
import com.mohanad.newsappkotlin.ui.view.LatestNewsView
import com.mohanad.newsappkotlin.ui.view.TrendingNewsView
import com.mohanad.newsappkotlin.ui.viewmodel.BottomHomeViewModel

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = BottomNavRoute.HomeNavItem.route) {
        composable(route = BottomNavRoute.HomeNavItem.route) {
            val viewModel = viewModel(modelClass = BottomHomeViewModel::class)
            BottomHomeView(viewModel = viewModel , navController = navController )
        }
        composable(route = BottomNavRoute.ExploreNavItem.route) {

        }
        composable(route = BottomNavRoute.BookmarkNavItem.route) {

        }
        composable(route = BottomNavRoute.ProfileNavItem.route) {

        }

        // Additional Screens
        composable(route = BottomNavRoute.HomeLatestNews.route ){
            val viewModel = viewModel(modelClass = BottomHomeViewModel::class)
            LatestNewsView(viewModel = viewModel)
        }
        composable(route = BottomNavRoute.HomeTrendingNews.route ){
            val viewModel = viewModel(modelClass = BottomHomeViewModel::class)
            TrendingNewsView(navController = navController,viewModel = viewModel)
        }
    }
}
