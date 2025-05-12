package com.example.moviesapp.presentation.search

import androidx.paging.PagingData
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.utils.UiState
import kotlinx.coroutines.flow.Flow

data class SearchMoviesState(
    val idledState: Boolean = true,
    val searchQuery: String = "",
    val moviesState: UiState<Flow<PagingData<Movie>>> = UiState.Loading,
)
