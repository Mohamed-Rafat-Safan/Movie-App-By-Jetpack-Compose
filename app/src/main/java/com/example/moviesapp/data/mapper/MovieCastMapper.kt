package com.example.moviesapp.data.mapper

import com.example.moviesapp.data.data_sources.local.MovieCastEntity
import com.example.moviesapp.data.data_sources.remote.dto.movie_cast.MovieCastDto
import com.example.moviesapp.domain.model.MovieCast


// MovieCastDto -> MovieCastEntity
fun MovieCastDto.toMovieCastEntity(
    movieId: Int,
): MovieCastEntity {
    return MovieCastEntity(
        adult = adult ?: false,
        cast_id = cast_id ?: -1,
        character = character ?: "",
        credit_id = credit_id ?: "",
        gender = gender ?: -1,
        id = id ?: -1,
        known_for_department = known_for_department ?: "",
        name = name ?: "",
        order = order ?: -1,
        original_name = original_name ?: "",
        popularity = popularity ?: 0.0,
        profile_path = profile_path ?: "",
        movieId = movieId
    )
}


// MovieCastEntity -> MovieCast
fun MovieCastEntity.toMovieCast(): MovieCast {
    return MovieCast(
        adult = adult ?: false,
        cast_id = cast_id ?: -1,
        character = character ?: "",
        credit_id = credit_id ?: "",
        gender = gender ?: -1,
        id = id ?: -1,
        known_for_department = known_for_department ?: "",
        name = name ?: "",
        order = order ?: -1,
        original_name = original_name ?: "",
        popularity = popularity ?: 0.0,
        profile_path = profile_path ?: "",
    )
}
