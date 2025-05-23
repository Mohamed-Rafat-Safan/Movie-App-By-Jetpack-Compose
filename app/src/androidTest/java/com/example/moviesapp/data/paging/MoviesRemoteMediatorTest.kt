package com.example.moviesapp.data.paging


import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviesapp.data.FakeMovie1
import com.example.moviesapp.data.FakeMovie2
import com.example.moviesapp.data.InMemoryDatabaseRule
import com.example.moviesapp.data.data_sources.local.MovieEntity
import com.example.moviesapp.data.data_sources.remote.MoviesApi
import com.example.moviesapp.data.data_sources.remote.dto.movie.MoviesResponse
import com.example.moviesapp.utils.Constant.POPULAR
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalPagingApi
class MoviesRemoteMediatorTest {
    private lateinit var moviesApi: MoviesApi

    @get:Rule
    val inMemoryDatabaseRule = InMemoryDatabaseRule()


    @Before
    fun setup() {
        moviesApi = mockk()
    }


    @Test
    fun refresh_load_returns_success_result_when_data_loaded() = runTest {
        // Arrange
        val movieDtoList = listOf(FakeMovie1, FakeMovie2)

        // fake movie response
        val apiResponse = MoviesResponse(
            page = 1,
            results = movieDtoList,
            total_pages = 2,
            total_results = 1
        )

        coEvery { moviesApi.getMoviesList(POPULAR, 1) } returns apiResponse

        val popularMediator = PopularMoviesRemoteMediator(moviesApi, inMemoryDatabaseRule.database)
        val topRatedMediator = TopRatedMoviesRemoteMediator(moviesApi, inMemoryDatabaseRule.database)

        val pagingState = PagingState<Int, MovieEntity>(
            pages = emptyList(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 20),
            leadingPlaceholderCount = 0
        )

        // Act
        val result = popularMediator.load(LoadType.REFRESH, pagingState)

        // Assert
        assertTrue(result is RemoteMediator.MediatorResult.Success)


        val pagingSource =
            inMemoryDatabaseRule.movieDao.getMoviesList() // افترض أنك عامل دالة sync أو test version

        val resultDb = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        val loadedData = (resultDb as PagingSource.LoadResult.Page).data
        assertEquals(123456, loadedData[0].id)
        println(loadedData[0].title)

    }
}