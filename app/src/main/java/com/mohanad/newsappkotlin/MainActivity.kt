package com.mohanad.newsappkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.mohanad.newsappkotlin.navigation.NavGraph
import com.mohanad.newsappkotlin.ui.theme.NewsAppKotlinTheme
// The Main App activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // My App Theme Composable
            NewsAppKotlinTheme {
                // The main app navigation controller
                val navController = rememberNavController()
                // Calling my app navigation graph
                NavGraph(navController = navController)
            }
        }
    }
}
