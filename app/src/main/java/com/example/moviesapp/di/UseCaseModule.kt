package com.example.moviesapp.di


import com.example.moviesapp.domain.repository.MoviesRepository
import com.example.moviesapp.domain.usecase.GetFavoriteMovies
import com.example.moviesapp.domain.usecase.GetMovieCast
import com.example.moviesapp.domain.usecase.GetMoviesByCategory
import com.example.moviesapp.domain.usecase.GetTrendingMovies
import com.example.moviesapp.domain.usecase.MovieUseCase
import com.example.moviesapp.domain.usecase.SearchMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCase(
        moviesRepository: MoviesRepository,
    ): MovieUseCase {
        return MovieUseCase(
            getTrendingMovies = GetTrendingMovies(moviesRepository),
            getMoviesByCategory = GetMoviesByCategory(moviesRepository),
            getMovieCast = GetMovieCast(moviesRepository),
            searchMovies = SearchMovies(moviesRepository),
            getFavoriteMovies = GetFavoriteMovies(moviesRepository),
        )
    }

}