package com.example.moviesapp.presentation.favorite_movies

import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.utils.UiState

data class FavoriteMovieState(
    val favoriteMovies: UiState<List<Movie>> = UiState.Loading,
    val favoriteMoviesFound: Boolean = false,
)