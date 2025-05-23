package com.example.moviesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.presentation.common.HideNavigationBarOnly
import com.example.moviesapp.presentation.intro.IntroScreen
import com.example.moviesapp.presentation.intro.SplashScreen


@Composable
fun MainNavGraph() {
    val navController = rememberNavController()

    // Hide navigation bar
    HideNavigationBarOnly()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(route = Screen.SplashScreen.route) { SplashScreen(navController) }
        composable(route = Screen.IntroScreen.route) { IntroScreen(navController) }
        composable(route = Screen.ContentNavGraph.route) { ContentNavGraph() }

    }
}

//        composable(
//            route = Screen.Details.route,
//            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
//        ) {
//            val itemId = it.arguments?.getInt("itemId") ?: 0
//            DetailsScreen(itemId = itemId)
//        }