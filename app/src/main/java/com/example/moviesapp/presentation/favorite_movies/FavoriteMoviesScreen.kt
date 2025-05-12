package com.example.moviesapp.presentation.favorite_movies

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.favorite_movies.component.FavoriteMoviesList
import com.example.moviesapp.utils.UiState

@Composable
fun FavoriteMoviesScreen(
    viewModel: FavoriteMovieViewModel,
    navigateToDetails: (Movie) -> Unit,
) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleFavoriteMovieIntent(FavoriteMovieIntent.GetFavoriteMovies)
    }

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),

    ) {
        if (!state.favoriteMoviesFound) {
            Image(
                painter = painterResource(R.drawable.movie),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(0.4f)
            )
        }

        when (val response = state.favoriteMovies) {
            is UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is UiState.Success -> {
                response.data?.let { favoriteMovies ->
                    FavoriteMoviesList(
                        favoriteMovies = favoriteMovies,
                        onMovieClick = { movie ->
                            navigateToDetails(movie)
                        }
                    )
                }
            }
            is UiState.Error -> {
                val errorMessage = (state.favoriteMovies as UiState.Error).message
                Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }

}