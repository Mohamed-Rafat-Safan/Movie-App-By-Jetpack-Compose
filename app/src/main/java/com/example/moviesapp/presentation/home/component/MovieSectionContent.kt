package com.example.moviesapp.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.common.DiscoverMovieCard
import com.example.moviesapp.presentation.common.MovieCard
import com.example.moviesapp.utils.Constant.DISCOVER
import com.example.moviesapp.utils.UiState
import kotlinx.coroutines.flow.Flow


@Composable
fun MovieSectionContent(
    movieCategory: String,
    uiState: UiState<Flow<PagingData<Movie>>>,
    onClick: (Movie) -> Unit,
) {
    when (uiState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> uiState.data?.let { pagingData ->
            val lazyPagingItems = remember { pagingData }.collectAsLazyPagingItems()
            ListMoviesContent(movieCategory, lazyPagingItems, onClick = onClick)
        }

        is UiState.Error -> Text("Error: ${uiState.message}", color = Color.Red)
    }
}


@Composable
fun ListMoviesContent(
    movieCategory: String,
    moviesItems: LazyPagingItems<Movie>,
    onClick: (Movie) -> Unit,
) {
    if (moviesItems.loadState.refresh is LoadState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(
            count = moviesItems.itemCount,
        ) { index ->
            val movie = moviesItems[index]
            if (movie != null) {
                if (movieCategory == DISCOVER) {
                    DiscoverMovieCard(movie = movie, onClick = { onClick(movie) })
                } else {
                    MovieCard(movie = movie, onClick = { onClick(movie) })
                }

            }
        }

        item {
            if (moviesItems.loadState.append is LoadState.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}