package com.example.moviesapp.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.moviesapp.data.data_sources.local.MovieDatabase
import com.example.moviesapp.data.data_sources.local.MovieEntity
import com.example.moviesapp.data.data_sources.remote.MoviesApi
import com.example.moviesapp.data.mapper.toMovieEntity
import com.example.moviesapp.utils.Constant.POPULAR
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PopularMoviesRemoteMediator (
    private val moviesApi: MoviesApi,
    private val movieDatabase: MovieDatabase,
) : RemoteMediator<Int, MovieEntity>() {

    private val movieDao = movieDatabase.movieDao
    var currentPage = 1
    var totalAvailablePages = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>,
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    Log.d("PopularMoviesRemoteMediator", "currentPage: $currentPage")
                    if (currentPage >= totalAvailablePages) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    currentPage + 1
                }
            }


            // list movies
            val moviesResponse = moviesApi.getMoviesList(category = POPULAR, page = loadKey)

            movieDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieDao.clearMoviesByCategory(POPULAR)
                }

                val listMovieEntity = moviesResponse.results.map { movieDto ->
                    movieDto.toMovieEntity(POPULAR)
                }

                movieDao.upsertMoviesList(movies = listMovieEntity)
            }

            currentPage = loadKey
            totalAvailablePages = moviesResponse.total_pages
            MediatorResult.Success(endOfPaginationReached = currentPage >= totalAvailablePages)

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

}