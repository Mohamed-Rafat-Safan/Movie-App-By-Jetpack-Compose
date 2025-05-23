package com.example.moviesapp.presentation.search

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.common.SearchBar
import com.example.moviesapp.presentation.search.component.SearchMoviesList
import com.example.moviesapp.utils.UiState

@Composable
fun SearchScreen(
    viewModel: SearchMoviesViewModel ,
    navigateToDetails: (Movie) -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .statusBarsPadding()
            .fillMaxSize()
    ) {

        SearchBar(
            text = state.searchQuery,
            isClickable = false,
            onValueChanged = { query ->
                viewModel.event(SearchMoviesIntent.UpdateSearchQuery(query))

                viewModel.event(SearchMoviesIntent.SearchMovies(query))
            },
            onClearClick = {
                viewModel.event(SearchMoviesIntent.ClearSearch)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (state.idledState) {
                Image(
                    painter = painterResource(R.drawable.movie),
                    contentDescription = null,
                    modifier = Modifier
                        .size(140.dp)
                        .align(Alignment.Center)
                        .alpha(0.4f)
                )
            }




            if (!state.idledState) {
                when (val response = state.moviesState) {
                    is UiState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    is UiState.Success -> {
                        response.data?.let { pagingData ->
                            val lazyPagingItems = pagingData.collectAsLazyPagingItems()

                            SearchMoviesList(
                                moviesItems = lazyPagingItems,
                                onMovieClick = { movie ->
                                    navigateToDetails(movie)
                                })
                        }
                    }

                    is UiState.Error -> {
                        val errorMessage = (state.moviesState as UiState.Error).message
                        Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }


    }

}