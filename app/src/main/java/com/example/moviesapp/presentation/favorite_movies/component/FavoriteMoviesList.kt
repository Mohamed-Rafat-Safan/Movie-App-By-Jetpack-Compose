package com.example.moviesapp.presentation.favorite_movies.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesapp.domain.model.Movie

@Composable
fun FavoriteMoviesList(
    favoriteMovies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(10.dp),
    ) {
        items(favoriteMovies.size) { index ->
            val movie = favoriteMovies[index]
            FavoriteMovieCard(movie = movie, onItemClick = onMovieClick)
        }
    }

}