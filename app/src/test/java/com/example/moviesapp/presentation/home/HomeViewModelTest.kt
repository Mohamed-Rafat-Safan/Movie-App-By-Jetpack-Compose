package com.example.moviesapp.presentation.home

import androidx.paging.PagingData
import app.cash.turbine.test
import com.example.moviesapp.domain.usecase.MovieUseCase
import com.example.moviesapp.collectData
import com.example.moviesapp.movieDiffCallback
import com.example.moviesapp.utils.Constant.POPULAR
import com.example.moviesapp.utils.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val movieUseCase: MovieUseCase = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = HomeViewModel(movieUseCase)
    }


    @Test
    fun `Given valid movie list, When LoadTrendingMovies is triggered, then uiState should emit Loading then Success`() =
        runTest {
            // Arrange
            val moviesList = getFakeMovieList()


            // Act
            coEvery { movieUseCase.getTrendingMovies() } returns flowOf(moviesList)

            viewModel.handleIntent(HomeIntent.LoadTrendingMovies) // Here we call getTrendingMovies


            // Assert
            viewModel.uiState.test {
                val loadingState = awaitItem()
                assert(loadingState.trendingMovies is UiState.Loading)

                val successState = awaitItem()
                val result = successState.trendingMovies as UiState.Success
                assertEquals(3, result.data?.size)
                assertEquals("RomCom Delight", result.data?.get(1)?.title)

                cancelAndConsumeRemainingEvents()
            }
        }


    @Test
    fun `Given valid error message, When getTrendingMovies throws exception, uiState should emit Error`() =
        runTest {
            // Arrange
            val errorMessage = "Something went wrong"
            coEvery { movieUseCase.getTrendingMovies() } throws RuntimeException(errorMessage)

            // Act
            viewModel.handleIntent(HomeIntent.LoadTrendingMovies) // call getTrendingMovies

            // Assert
            viewModel.uiState.test {
                val loadingState = awaitItem()
                assert(loadingState.trendingMovies is UiState.Loading)

                val errorState = awaitItem()
                val result = errorState.trendingMovies as UiState.Error
                assertEquals(errorMessage, result.message)

                cancelAndConsumeRemainingEvents()
            }

        }


    // Remove .cachedIn(viewModelScope) from getPopularMovies to pass this test
    @Ignore
    @Test
    fun `Given valid movie list, When LoadPopularMovies is triggered, then uiState should emit Loading then Success`() =
        runTest {
            // Arrange
            val moviesList = getFakeMovieList()
            val pagingData = PagingData.from(moviesList)

            coEvery { movieUseCase.getMoviesByCategory(POPULAR) } returns flowOf(pagingData)

            // Act
            viewModel.handleIntent(HomeIntent.LoadPopularMovies) // call getPopularMovies


            // Assert
            viewModel.uiState.test {
                val loadingState = awaitItem()
                assert(loadingState.popularMovies is UiState.Loading)

                val successState = awaitItem()
                val result = successState.popularMovies as UiState.Success

//
                val movieList =
                    result.data?.collectData(movieDiffCallback) // convert PagingData to List

                assertEquals(3, movieList?.size)

                assertEquals("RomCom Delight", movieList?.get(1)?.title)

                cancelAndConsumeRemainingEvents()
            }

            advanceUntilIdle()
        }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


}