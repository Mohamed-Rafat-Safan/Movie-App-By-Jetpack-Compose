package com.example.moviesapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
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
class SearchMoviesViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchMoviesState())
    val uiState: StateFlow<SearchMoviesState> = _uiState

    fun event(intent: SearchMoviesIntent) {
        when (intent) {
            is SearchMoviesIntent.UpdateSearchQuery -> {
                _uiState.update { it.copy(searchQuery = intent.searchQuery) }
            }
            is SearchMoviesIntent.SearchMovies -> searchMovies(intent.query)
            is SearchMoviesIntent.ClearSearch -> clearSearch()
        }
    }


    private fun searchMovies(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(moviesState = UiState.Loading) }
            try {
                val movies = movieUseCase.searchMovies(query).cachedIn(viewModelScope)

                _uiState.update { it.copy(moviesState = UiState.Success(movies)) }
                _uiState.update { it.copy(idledState = false) }

            } catch (e: Exception) {
                _uiState.update { it.copy(moviesState = UiState.Error(e.message.toString())) }
            }
        }
    }

    private fun clearSearch() {
        _uiState.update { it.copy(idledState = true) }
    }

}