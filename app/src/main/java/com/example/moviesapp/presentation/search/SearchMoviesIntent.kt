package com.example.moviesapp.presentation.search

sealed class SearchMoviesIntent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchMoviesIntent()
    data class SearchMovies(val query: String) : SearchMoviesIntent()
    object ClearSearch : SearchMoviesIntent()
}