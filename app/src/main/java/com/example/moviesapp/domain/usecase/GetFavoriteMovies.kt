package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.mapper.toMovie
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteMovies(
    private val moviesRepository: MoviesRepository,
) {
    operator fun invoke(): Flow<List<Movie>> =
        moviesRepository.getAllFavoriteMovies().map { listFavoriteMovieEntity ->
            listFavoriteMovieEntity.map { favoriteMovieEntity ->
                favoriteMovieEntity.toMovie()
            }
        }
}