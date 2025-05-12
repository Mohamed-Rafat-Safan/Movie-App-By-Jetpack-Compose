package com.example.moviesapp.presentation.favorite_movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.repository.MoviesRepository
import com.example.moviesapp.domain.usecase.MovieUseCase
import com.example.moviesapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val repository: MoviesRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(FavoriteMovieState())
    val uiState: StateFlow<FavoriteMovieState> = _uiState

    fun handleFavoriteMovieIntent(intent: FavoriteMovieIntent) {
        when (intent) {
            is FavoriteMovieIntent.GetFavoriteMovies -> getFavoriteMovies()
        }

    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            try {
                movieUseCase.getFavoriteMovies()
                    .collect { favoriteMovies ->
                        if (favoriteMovies.isEmpty()) {
                            _uiState.update {
                                it.copy(favoriteMoviesFound = false)
                            }
                        } else {
                            _uiState.update {
                                it.copy(
                                    favoriteMovies = UiState.Success(favoriteMovies),
                                    favoriteMoviesFound = true
                                )
                            }
                        }
                    }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(favoriteMovies = UiState.Error(e.message ?: "Unknown error"))
                }
            }
        }
    }
}