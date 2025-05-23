package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.data_sources.local.MovieEntity


fun getFakeMovieEntityList(): List<MovieEntity> {
    return listOf(
        MovieEntity(
            id = 1,
            category = "Popular",
            adult = false,
            backdrop_path = "/path_to_backdrop1.jpg",
            genre_ids = "[28, 12]",
            original_language = "en",
            original_title = "Fake Movie One",
            overview = "This is the overview of Fake Movie One.",
            popularity = 123.45,
            poster_path = "/poster1.jpg",
            release_date = "2024-01-15",
            title = "Fake Movie One",
            video = false,
            vote_average = 7.8,
            vote_count = 1000
        ),
        MovieEntity(
            id = 2,
            category = "TopRated",
            adult = false,
            backdrop_path = "/path_to_backdrop2.jpg",
            genre_ids = "[18, 80]",
            original_language = "fr",
            original_title = "Faux Film Deux",
            overview = "Un film fictif avec une intrigue passionnante.",
            popularity = 98.7,
            poster_path = "/poster2.jpg",
            release_date = "2023-11-22",
            title = "Fake Movie Two",
            video = false,
            vote_average = 8.6,
            vote_count = 750
        ),
        MovieEntity(
            id = 3,
            category = "Upcoming",
            adult = true,
            backdrop_path = "/path_to_backdrop3.jpg",
            genre_ids = "[27, 53]",
            original_language = "ja",
            original_title = "フェイク映画三",
            overview = "これはフェイク映画三の概要です。",
            popularity = 150.0,
            poster_path = "/poster3.jpg",
            release_date = "2025-09-10",
            title = "Fake Movie Three",
            video = true,
            vote_average = 6.2,
            vote_count = 320
        )
    )
}
