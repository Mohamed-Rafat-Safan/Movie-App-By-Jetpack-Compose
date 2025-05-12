package com.example.moviesapp.presentation.home

import androidx.paging.PagingData
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.utils.UiState
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val trendingMovies: UiState<List<Movie>> = UiState.Loading,

    val popularMovies: UiState<Flow<PagingData<Movie>>> = UiState.Loading,
    val topRatedMovies: UiState<Flow<PagingData<Movie>>> = UiState.Loading,
    val upcomingMovies: UiState<Flow<PagingData<Movie>>> = UiState.Loading,
    val discoverMovies: UiState<Flow<PagingData<Movie>>> = UiState.Loading,
    val nowPlayingMovies: UiState<Flow<PagingData<Movie>>> = UiState.Loading,
)
