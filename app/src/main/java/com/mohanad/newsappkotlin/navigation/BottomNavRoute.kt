package com.mohanad.newsappkotlin.navigation

import com.mohanad.newsappkotlin.R

// The Bottom and additional Navigation routes
sealed class BottomNavRoute (val route:String, val label:String, val icon:Int, val selectedIcon:Int){
    // BottomHome Route
    data object HomeNavItem:BottomNavRoute(route = "home", label = "Home", icon = R.drawable.ic_home , selectedIcon = R.drawable.ic_home_selected)
    // BottomExplore Route
    data object ExploreNavItem:BottomNavRoute(route = "explore", label = "Explore", icon = R.drawable.ic_explore , selectedIcon = R.drawable.ic_explore_selected)
    // BottomBookmark Route
    data object BookmarkNavItem:BottomNavRoute(route = "bookmark", label = "Bookmark", icon = R.drawable.ic_bookmark , selectedIcon = R.drawable.ic_bookmark_selected)
    // BottomProfile Route
    data object ProfileNavItem:BottomNavRoute(route = "profile", label = "Profile", icon = R.drawable.ic_profile , selectedIcon = R.drawable.ic_profile_selected)
    // Additional Routes

    // LatestNews Route
    data object HomeLatestNews:BottomNavRoute(route = "latest news", label = "Home", icon = R.drawable.ic_home , selectedIcon = R.drawable.ic_home_selected)
    // TrendingNews Route
    data object HomeTrendingNews:BottomNavRoute(route = "trending news", label = "Home", icon = R.drawable.ic_home , selectedIcon = R.drawable.ic_home_selected)
    // NewsArticleDetails Route
    data object DetailsView:BottomNavRoute(route = "details view", label = "Home", icon = R.drawable.ic_home , selectedIcon = R.drawable.ic_home_selected)
    // WebView Route
    data object WebView:BottomNavRoute(route = "web view", label = "Home", icon = R.drawable.ic_home , selectedIcon = R.drawable.ic_home_selected)
    // EditProfile Route
    data object EditProfile:BottomNavRoute(route = "edit profile", label = "Home", icon = R.drawable.ic_home , selectedIcon = R.drawable.ic_home_selected)
}