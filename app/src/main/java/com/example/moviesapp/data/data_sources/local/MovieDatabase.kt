package com.example.moviesapp.data.data_sources.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class, MovieCastEntity::class, FavoriteMovieEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}