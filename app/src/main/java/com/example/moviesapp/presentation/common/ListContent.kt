package com.example.moviesapp.presentation.common

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.moviesapp.domain.model.Movie


@ExperimentalCoilApi
@Composable
fun ListContent(moviesItems: LazyPagingItems<Movie>, onClick: (Movie) -> Unit) {
    Log.d("Error", moviesItems.loadState.toString())

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(
            count = moviesItems.itemCount,
        ) { index ->
            val movie = moviesItems[index]
            if (movie != null) {
                MovieCard(movie = movie, onClick = { onClick(movie) })
            }
        }

        moviesItems.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator(Modifier.fillMaxWidth()) }
                }
                loadState.refresh is LoadState.Error -> {
                    item { Text("Error loading movies", color = Color.Red) }
                }
            }
        }

    }

}

