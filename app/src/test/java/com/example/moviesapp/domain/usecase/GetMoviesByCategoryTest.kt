package com.example.moviesapp.domain.usecase

import androidx.paging.PagingData
import com.example.moviesapp.collectData
import com.example.moviesapp.domain.repository.MoviesRepository
import com.example.moviesapp.movieDiffCallback
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMoviesByCategoryTest {
    private lateinit var getMoviesByCategory: GetMoviesByCategory
    private lateinit var moviesRepository: MoviesRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        moviesRepository = mockk()
        getMoviesByCategory = GetMoviesByCategory(moviesRepository)
    }

    @Test
    fun `Given valid category, when getMoviesByCategory is called, then return Flow of PagingData of Movie`() =
        runTest {
            // Arrange
            val category = "Action"
            val movieEntityList = getFakeMovieEntityList()

            val pagingData = PagingData.from(movieEntityList)

            every { moviesRepository.getMoviesListByCategory(category) } returns flowOf(pagingData)

            // Act
            val result = getMoviesByCategory(category)

            // Assert
            val movieList = result.collectData(movieDiffCallback)  // this convert PagingData to List

            assertEquals(3, movieList.size)
            assertEquals("Fake Movie One", movieList[0].title)

        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}


