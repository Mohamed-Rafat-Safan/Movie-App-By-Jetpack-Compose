package com.example.moviesapp.data.data_sources.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesapp.utils.Constant.FAVORITE_MOVIES_TABLE
import com.example.moviesapp.utils.Constant.MOVIES_CAST_TABLE
import com.example.moviesapp.utils.Constant.MOVIES_TABLE
import com.example.moviesapp.utils.Constant.TRENDING

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMoviesList(movies: List<MovieEntity>)  // Here, i add list of movies to the database

    @Query("SELECT* FROM $MOVIES_TABLE")
    fun getMoviesList(): PagingSource<Int, MovieEntity>

    @Query("SELECT* FROM $MOVIES_TABLE WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity

    @Query("SELECT* FROM $MOVIES_TABLE WHERE category = :category")
    fun getMoviesListByCategory(category: String): PagingSource<Int, MovieEntity>

    @Query("SELECT* FROM $MOVIES_TABLE WHERE category = '$TRENDING'")
    fun getTrendingMovies(): List<MovieEntity>

    @Query("DELETE FROM $MOVIES_TABLE WHERE category = :category")
    suspend fun clearMoviesByCategory(category: String)


    // Movie Cast

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMovieCastList(movieCast: List<MovieCastEntity>)

    @Query("SELECT* FROM $MOVIES_CAST_TABLE WHERE movieId = :movieId")
    fun getMovieCastById(movieId: Int): List<MovieCastEntity>


    // Favorite Movies

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertFavoriteMovie(favoriteMovie: FavoriteMovieEntity)

    @Query("SELECT* FROM $FAVORITE_MOVIES_TABLE")
    suspend fun getAllFavoriteMovies(): List<FavoriteMovieEntity>

    @Query("SELECT* FROM $FAVORITE_MOVIES_TABLE WHERE id = :movieId")
    suspend fun getFavoriteMovieById(movieId: Int): FavoriteMovieEntity?

    @Query("DELETE FROM $FAVORITE_MOVIES_TABLE WHERE id = :movieId")
    suspend fun deleteFavoriteMovie(movieId: Int)

}