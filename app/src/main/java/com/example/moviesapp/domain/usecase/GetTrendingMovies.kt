package com.example.moviesapp.domain.usecase

import android.util.Log
import com.example.moviesapp.data.mapper.toMovie
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.repository.MoviesRepository
import com.example.moviesapp.utils.Constant.TRENDING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTrendingMovies(
    private val moviesRepository: MoviesRepository,
) {
    operator fun invoke(): Flow<List<Movie>> {
        return moviesRepository.getTrendingMovies().map { listMovieEntity ->
            Log.i("TrendingMoviesUseCase", "getTrendingMovies: $listMovieEntity")

            listMovieEntity.map {movieEntity ->
                movieEntity.toMovie(TRENDING)  // convert MovieEntity to Movie
            }
        }
    }
}