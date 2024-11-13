package com.mohanad.newsappkotlin.navigation

import com.mohanad.newsappkotlin.R

sealed class BottomNavRoute (val route:String, val label:String, val icon:Int, val selectedIcon:Int){
    data object HomeNavItem:BottomNavRoute(route = "home", label = "Home", icon = R.drawable.ic_home , selectedIcon = R.drawable.ic_home_selected)
    data object ExploreNavItem:BottomNavRoute(route = "explore", label = "Explore", icon = R.drawable.ic_explore , selectedIcon = R.drawable.ic_explore_selected)
    data object BookmarkNavItem:BottomNavRoute(route = "bookmark", label = "Bookmark", icon = R.drawable.ic_bookmark , selectedIcon = R.drawable.ic_bookmark_selected)
    data object ProfileNavItem:BottomNavRoute(route = "profile", label = "Profile", icon = R.drawable.ic_profile , selectedIcon = R.drawable.ic_profile_selected)
    // Additional Routes
    data object HomeLatestNews:BottomNavRoute(route = "latest news", label = "Home", icon = R.drawable.ic_home , selectedIcon = R.drawable.ic_home_selected)
    data object HomeTrendingNews:BottomNavRoute(route = "trending news", label = "Home", icon = R.drawable.ic_home , selectedIcon = R.drawable.ic_home_selected)
}