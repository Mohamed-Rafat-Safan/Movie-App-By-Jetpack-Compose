package com.example.moviesapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.moviesapp.data.data_sources.local.MovieDatabase
import com.example.moviesapp.data.data_sources.remote.MoviesApi
import com.example.moviesapp.data.repository.MoviesRepositoryImpl
import com.example.moviesapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApi: MoviesApi,
        movieDatabase: MovieDatabase
    ): MoviesRepository {
        return MoviesRepositoryImpl(movieApi, movieDatabase)
    }

}