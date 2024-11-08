package com.mohanad.newsappkotlin.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.mohanad.newsappkotlin.navigation.BottomNavRoute

// Home Screen
@Composable
fun HomeView(navController: NavHostController){
    val listOfItem = listOf(BottomNavRoute.HomeNavItem,BottomNavRoute.ExploreNavItem,BottomNavRoute.BookmarkNavItem,BottomNavRoute.ProfileNavItem)
    Scaffold (
        bottomBar = {
           // BottomNavBar(list = listOfItem, navController = navController)
        }
    ){
        ConstraintLayout (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){

        }
    }
}