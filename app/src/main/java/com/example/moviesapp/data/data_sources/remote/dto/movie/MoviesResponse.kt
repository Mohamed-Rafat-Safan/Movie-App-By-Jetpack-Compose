package com.example.moviesapp.data.data_sources.remote.dto.movie

data class MoviesResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)