package com.example.moviesapp.presentation.details

import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MovieCast
import com.example.moviesapp.utils.UiState

data class MovieDetailsState(
    val movieState: UiState<Movie> = UiState.Loading,
    val favoriteState: Boolean = false,
    val movieCastState: UiState<List<MovieCast>> = UiState.Loading,
)
