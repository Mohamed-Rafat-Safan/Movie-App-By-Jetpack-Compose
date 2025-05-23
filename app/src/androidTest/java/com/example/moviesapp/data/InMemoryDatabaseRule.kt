package com.example.moviesapp.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.moviesapp.data.data_sources.local.MovieDao
import com.example.moviesapp.data.data_sources.local.MovieDatabase
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class InMemoryDatabaseRule : TestWatcher() {
    lateinit var database: MovieDatabase
        private set

    val movieDao: MovieDao
        get() = database.movieDao

    override fun starting(description: Description) {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    override fun finished(description: Description) {
        database.close()
    }
}
