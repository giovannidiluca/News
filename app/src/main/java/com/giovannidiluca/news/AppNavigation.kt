package com.giovannidiluca.news

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "news_list") {
        composable("news_list") {
            NewsListScreen(navController)
        }
        composable(route = "news_details") {
            NewsDetailsScreen()
        }
    }
}
