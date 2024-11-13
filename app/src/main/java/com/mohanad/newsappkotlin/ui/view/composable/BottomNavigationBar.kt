package com.mohanad.newsappkotlin.ui.view.composable

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mohanad.newsappkotlin.navigation.BottomNavRoute
import com.mohanad.newsappkotlin.ui.theme.mainBlackGrey
import com.mohanad.newsappkotlin.ui.theme.mainBlue

@Composable
fun BottomNavigationBar(navController: NavHostController , list:List<BottomNavRoute>){
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
    NavigationBar(
        containerColor = Color.Transparent,
    ){
        list.forEach{ navItem ->
            val isSelected = currentRoute == navItem.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(navItem.route){
                        popUpTo(navController.graph.startDestinationRoute!!){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Image(
                        painter = painterResource(id = if(isSelected) navItem.selectedIcon else navItem.icon),
                        contentDescription = navItem.label)
                },
                label = {
                    Text(
                        text = navItem.label,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = mainBlue,
                    unselectedTextColor = mainBlackGrey,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}