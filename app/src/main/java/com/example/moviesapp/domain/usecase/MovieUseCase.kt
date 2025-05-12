package com.example.moviesapp.domain.usecase

data class MovieUseCase(
    val getTrendingMovies: GetTrendingMovies,
    val getMoviesByCategory: GetMoviesByCategory,
    val getMovieCast: GetMovieCast,
    val searchMovies: SearchMovies,
    val getFavoriteMovies: GetFavoriteMovies,
)
