package com.example.moviesapp.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.bottom_navigation.BottomBar
import com.example.moviesapp.presentation.bottom_navigation.BottomNavItem
import com.example.moviesapp.presentation.details.MovieDetailsScreen
import com.example.moviesapp.presentation.details.MovieDetailsViewModel
import com.example.moviesapp.presentation.favorite_movies.FavoriteMovieViewModel
import com.example.moviesapp.presentation.favorite_movies.FavoriteMoviesScreen
import com.example.moviesapp.presentation.home.HomeScreen
import com.example.moviesapp.presentation.home.HomeViewModel
import com.example.moviesapp.presentation.search.SearchMoviesViewModel
import com.example.moviesapp.presentation.search.SearchScreen

@Composable
fun ContentNavGraph() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavItem(Icons.Default.Home, "Home"),
            BottomNavItem(Icons.Default.Search, "Search"),
            BottomNavItem(Icons.Default.Favorite, "Favorite")
        )
    }


    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    selectedItem = when (backStackState?.destination?.route) {
        Screen.HomeScreen.route -> 0
        Screen.SearchScreen.route -> 1
        Screen.FavoriteMoviesScreen.route -> 2
        else -> 0
    }


    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Screen.HomeScreen.route ||
                backStackState?.destination?.route == Screen.SearchScreen.route ||
                backStackState?.destination?.route == Screen.FavoriteMoviesScreen.route
    }


    Scaffold(
        bottomBar = {
            if (isBottomBarVisible) {
                BottomBar(
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Screen.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Screen.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Screen.FavoriteMoviesScreen.route
                            )
                        }
                    }
                )

            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.blackBackground))
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0.5f)
            )

            NavHost(
                navController = navController,
                startDestination = Screen.HomeScreen.route,
            ) {
                composable(route = Screen.HomeScreen.route) { backStackEntry ->

                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(Screen.HomeScreen.route)
                    }

                    val homeViewModel = hiltViewModel<HomeViewModel>(parentEntry)

                    HomeScreen(
                        viewModel = homeViewModel,
                        onClickSearchBar = {
                            selectedItem = 1
                            navController.navigate(Screen.SearchScreen.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }  // 1 ==> translate to search screen
                        },
                        navigateToDetails = { movie ->
                            navigateToDetails(
                                navController = navController,
                                movie = movie
                            )
                        })
                }

                composable(route = Screen.SearchScreen.route) {
                    val viewModel: SearchMoviesViewModel = hiltViewModel()
                    SearchScreen(
                        viewModel = viewModel,
                        navigateToDetails = { movie ->
                            navigateToDetails(
                                navController = navController,
                                movie = movie
                            )
                        }
                    )
                }
                composable(route = Screen.FavoriteMoviesScreen.route) {
                    val viewModel: FavoriteMovieViewModel = hiltViewModel()
                    FavoriteMoviesScreen(
                        viewModel, navigateToDetails = { movie ->
                            navigateToDetails(
                                navController = navController,
                                movie = movie
                            )
                        }
                    )
                }

                composable(route = Screen.DetailsScreen.route) { backStackEntry ->
                    val viewModel: MovieDetailsViewModel = hiltViewModel()

                    navController.previousBackStackEntry?.savedStateHandle?.get<Movie?>("movie")
                        ?.let { movie ->
                            MovieDetailsScreen(movie = movie, viewModel = viewModel, onBackClick = {
                                navController.popBackStack()
                            })
                        }
                }
            }

        }


    }
}


fun navigateToDetails(navController: NavHostController, movie: Movie) {
    // this will save the article in the savedStateHandle
    navController.currentBackStackEntry?.savedStateHandle?.set("movie", movie)
    navController.navigate(
        route = Screen.DetailsScreen.route
    )
}


private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
