package com.example.moviesapp.domain.repository

import androidx.paging.PagingData
import com.example.moviesapp.data.data_sources.local.FavoriteMovieEntity
import com.example.moviesapp.data.data_sources.local.MovieCastEntity
import com.example.moviesapp.data.data_sources.local.MovieEntity
import com.example.moviesapp.data.data_sources.remote.dto.movie.MovieDto
import com.example.moviesapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getTrendingMovies(): Flow<List<MovieEntity>>

    fun getMoviesListByCategory(
        category: String,
    ): Flow<PagingData<MovieEntity>>


    fun getMovieCast(movieId: Int): Flow<List<MovieCastEntity>>

    fun searchMovies(query: String): Flow<PagingData<MovieDto>>


    suspend fun addFavoriteMovie(movie: Movie)

    suspend fun getFavoriteMovieById(movieId: Int): Movie?

    suspend fun deleteFavoriteMovie(movieId: Int)

    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

}