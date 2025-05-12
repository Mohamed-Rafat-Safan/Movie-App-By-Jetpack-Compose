package com.example.moviesapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviesapp.data.mapper.toMovie
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchMovies(
    private val moviesRepository: MoviesRepository,
) {
    operator fun invoke(query: String): Flow<PagingData<Movie>> {
        return moviesRepository.searchMovies(query).map { pagingData ->
            pagingData.map { movieDto ->
                movieDto.toMovie() // Assuming you have a mapper function
            }
        }
    }
}