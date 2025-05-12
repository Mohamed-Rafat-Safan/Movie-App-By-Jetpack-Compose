package com.example.moviesapp.data.data_sources.remote.dto.movie_cast

import com.google.gson.annotations.SerializedName

data class MovieCastResponse(
    @SerializedName("cast")
    val movieCastDto: List<MovieCastDto>,
    @SerializedName("crew")
    val movieCrewDto: List<MovieCrewDto>,
    @SerializedName("id")
    val movieId: Int,
)