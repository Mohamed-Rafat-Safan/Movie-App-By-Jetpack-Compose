package com.example.moviesapp.presentation.search.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.moviesapp.domain.model.Movie

@Composable
fun SearchMoviesList(
    modifier: Modifier = Modifier,
    moviesItems: LazyPagingItems<Movie>,
    onMovieClick: (Movie) -> Unit,
) {
    if (moviesItems.loadState.refresh is LoadState.Loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(count = moviesItems.itemCount) {
            moviesItems[it]?.let { movie ->
                SearchMovieCard (movie = movie, onClick = onMovieClick)
            }
        }

        item {
            if (moviesItems.loadState.append is LoadState.Loading) {
                CircularProgressIndicator()
            }
        }
    }

}