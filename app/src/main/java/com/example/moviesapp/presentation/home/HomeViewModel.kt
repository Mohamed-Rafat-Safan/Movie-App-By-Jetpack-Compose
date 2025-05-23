package com.example.moviesapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.moviesapp.domain.usecase.MovieUseCase
import com.example.moviesapp.utils.Constant.DISCOVER
import com.example.moviesapp.utils.Constant.NOW_PLAYING
import com.example.moviesapp.utils.Constant.POPULAR
import com.example.moviesapp.utils.Constant.TOP_RATED
import com.example.moviesapp.utils.Constant.UPCOMING
import com.example.moviesapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadTrendingMovies -> getTrendingMovies()
            is HomeIntent.LoadPopularMovies -> getPopularMovies()
            is HomeIntent.LoadTopRatedMovies -> getTopRatedMovies()
            is HomeIntent.LoadUpcomingMovies -> getUpcomingMovies()
            is HomeIntent.LoadDiscoverMovies -> getDiscoverMovies()
            is HomeIntent.LoadNowPlayingMovies -> getNowPlayingMovies()
        }
    }


    private fun getTrendingMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(trendingMovies = UiState.Loading) }
            try {
                movieUseCase.getTrendingMovies().collect { trendingMoviesList ->
                    _uiState.update { it.copy(trendingMovies = UiState.Success(trendingMoviesList)) }
                }
            } catch (e: Exception) {
//                Log.d("TrendingMoviesError", "getTrendingMovies: ${e.toString()}")
                _uiState.update { it.copy(trendingMovies = UiState.Error(e.message.toString())) }
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(popularMovies = UiState.Loading) }
            try {
                val popularMovies =
                    movieUseCase.getMoviesByCategory(POPULAR).cachedIn(viewModelScope)
                _uiState.update { it.copy(popularMovies = UiState.Success(popularMovies)) }

            } catch (e: Exception) {
                _uiState.update { it.copy(popularMovies = UiState.Error(e.message.toString())) }
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(topRatedMovies = UiState.Loading) }
            try {
                val topRatedMovies =
                    movieUseCase.getMoviesByCategory(TOP_RATED).cachedIn(viewModelScope)
                _uiState.update { it.copy(topRatedMovies = UiState.Success(topRatedMovies)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(topRatedMovies = UiState.Error(e.message.toString())) }
            }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(upcomingMovies = UiState.Loading) }
            try {
                val upcomingMovies =
                    movieUseCase.getMoviesByCategory(UPCOMING).cachedIn(viewModelScope)
                _uiState.update { it.copy(upcomingMovies = UiState.Success(upcomingMovies)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(upcomingMovies = UiState.Error(e.message.toString())) }
            }
        }
    }

    private fun getDiscoverMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(discoverMovies = UiState.Loading) }
            try {
                val discoverMovies =
                    movieUseCase.getMoviesByCategory(DISCOVER).cachedIn(viewModelScope)
                        .cachedIn(viewModelScope)
                _uiState.update { it.copy(discoverMovies = UiState.Success(discoverMovies)) }

            } catch (e: Exception) {
                _uiState.update { it.copy(discoverMovies = UiState.Error(e.message.toString())) }
            }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(nowPlayingMovies = UiState.Loading) }
            try {
                val nowPlayingMovies =
                    movieUseCase.getMoviesByCategory(NOW_PLAYING).cachedIn(viewModelScope)
                _uiState.update { it.copy(nowPlayingMovies = UiState.Success(nowPlayingMovies)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(nowPlayingMovies = UiState.Error(e.message.toString())) }
            }
        }
    }


}
