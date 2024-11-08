package com.mohanad.newsappkotlin.navigation

import com.mohanad.newsappkotlin.R

sealed class BottomNavRoute (val route:String, val label:String, val icon:Int){
    data object HomeNavItem:BottomNavRoute(route = NavRoute.HomeItem.route, label = "Home", icon = R.drawable.ic_home)
    data object ExploreNavItem:BottomNavRoute(route = NavRoute.ExploreItem.route, label = "Explore", icon = R.drawable.ic_explore)
    data object BookmarkNavItem:BottomNavRoute(route = NavRoute.BookmarkItem.route, label = "Bookmark", icon = R.drawable.ic_bookmark)
    data object ProfileNavItem:BottomNavRoute(route = NavRoute.ProfileItem.route, label = "Profile", icon = R.drawable.ic_profile)
}