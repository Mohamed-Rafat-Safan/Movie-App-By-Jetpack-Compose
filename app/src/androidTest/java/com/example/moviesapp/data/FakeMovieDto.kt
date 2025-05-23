package com.example.moviesapp.data

import com.example.moviesapp.data.data_sources.remote.dto.movie.MovieDto

val FakeMovie1 = MovieDto(
    adult = false,
    backdrop_path = "/abc123backdrop.jpg",
    genre_ids = listOf(28, 12, 16), // Action, Adventure, Animation
    id = 123456,
    original_language = "en",
    original_title = "The Fake2 Adventure",
    overview = "This is a mock overview of a fake movie used for testing purposes.",
    popularity = 987.65,
    poster_path = "/abc123poster.jpg",
    release_date = "2025-05-07",
    media_type = "movie",
    title = "The Movie Fake 1 Adventure",
    video = false,
    vote_average = 7.8,
    vote_count = 8569
)

val FakeMovie2 = MovieDto(
    adult = false,
    backdrop_path = "/abc123backdrop.jpg",
    genre_ids = listOf(12, 89, 17), // Action, Adventure, Animation
    id = 123555,
    original_language = "en",
    original_title = "The Fake2 Adventure",
    overview = "This is a mock overview of a fake movie used for testing purposes.",
    popularity = 725.65,
    poster_path = "/abc123poster.jpg",
    release_date = "2025-01-01",
    media_type = "movie",
    title = "The Movie Fake 2 Adventure",
    video = false,
    vote_average = 2.9,
    vote_count = 3210
)
