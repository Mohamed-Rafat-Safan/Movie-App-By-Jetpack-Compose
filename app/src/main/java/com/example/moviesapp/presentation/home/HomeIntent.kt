package com.example.moviesapp.presentation.home

sealed class HomeIntent {
    object LoadTrendingMovies : HomeIntent()
    object LoadPopularMovies : HomeIntent()
    object LoadTopRatedMovies : HomeIntent()
    object LoadUpcomingMovies : HomeIntent()
    object LoadDiscoverMovies : HomeIntent()
    object LoadNowPlayingMovies : HomeIntent()
}
