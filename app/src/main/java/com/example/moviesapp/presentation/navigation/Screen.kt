package com.example.moviesapp.presentation.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object IntroScreen : Screen("intro_screen")
    object ContentNavGraph : Screen("content_nav_graph")


    object HomeScreen : Screen("home_screen")
    object SearchScreen : Screen("search_screen")
    object FavoriteMoviesScreen : Screen("favorite_movies_screen")
    object DetailsScreen : Screen("details_screen")
}