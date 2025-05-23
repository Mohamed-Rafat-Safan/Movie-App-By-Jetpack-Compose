package com.example.moviesapp.presentation.home

import com.example.moviesapp.domain.model.Movie


fun getFakeMovieList(): List<Movie> {
    return listOf(
        Movie(
            adult = false,
            backdrop_path = "/path/to/backdrop1.jpg",
            genre_ids = listOf(28, 12),
            id = 101,
            original_language = "en",
            original_title = "Fake Action Movie",
            overview = "An action-packed adventure of a hero saving the world.",
            popularity = 123.45,
            poster_path = "/path/to/poster1.jpg",
            release_date = "2025-01-01",
            title = "Fake Action Movie",
            video = false,
            vote_average = 7.8,
            vote_count = 1500,
            category = "Action"
        ),
        Movie(
            adult = false,
            backdrop_path = "/path/to/backdrop2.jpg",
            genre_ids = listOf(35, 10749),
            id = 102,
            original_language = "en",
            original_title = "RomCom Delight",
            overview = "A hilarious and heartwarming romantic comedy.",
            popularity = 98.76,
            poster_path = "/path/to/poster2.jpg",
            release_date = "2024-12-15",
            title = "RomCom Delight",
            video = false,
            vote_average = 6.5,
            vote_count = 800,
            category = "Romance"
        ),
        Movie(
            adult = false,
            backdrop_path = "/path/to/backdrop3.jpg",
            genre_ids = listOf(27, 53),
            id = 103,
            original_language = "en",
            original_title = "Haunted Secrets",
            overview = "A chilling horror mystery set in an abandoned mansion.",
            popularity = 75.12,
            poster_path = "/path/to/poster3.jpg",
            release_date = "2023-10-31",
            title = "Haunted Secrets",
            video = false,
            vote_average = 8.2,
            vote_count = 3200,
            category = "Horror"
        )
    )

}