package com.mohanad.newsappkotlin.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.mohanad.newsappkotlin.navigation.BottomNavGraph
import com.mohanad.newsappkotlin.navigation.BottomNavRoute
import com.mohanad.newsappkotlin.ui.view.composable.BottomNavigationBar

// Home Screen
@Composable
fun HomeView(){
    val navController = rememberNavController()
    val listOfItem = listOf(BottomNavRoute.HomeNavItem,BottomNavRoute.ExploreNavItem,BottomNavRoute.BookmarkNavItem,BottomNavRoute.ProfileNavItem)
    Scaffold (
        bottomBar = {
           BottomNavigationBar(navController = navController, list = listOfItem)
        }
    ){
        ConstraintLayout (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            BottomNavGraph(navController = navController)
        }
    }
}