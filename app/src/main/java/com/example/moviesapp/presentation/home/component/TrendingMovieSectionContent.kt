package com.example.moviesapp.presentation.home.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.common.AutoSlidingCarousel
import com.example.moviesapp.utils.UiState

@Composable
fun TrendingMovieSectionContent(uiState: UiState<List<Movie>>) {
    when (uiState) {
        is UiState.Loading -> {
//            Log.d("MovieLoading", "MovieSectionContent: Loading")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> uiState.data?.let { listMovies ->
//            Log.d("MovieSuccess", "listMovies: $listMovies")
            DisplayHomeSlider(listMovies)
        }

        is UiState.Error -> {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = Color(android.graphics.Color.parseColor("#2f2f39"))
                    )
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.movie_trend), // Replace with your placeholder
                    contentDescription = "Cast Member Placeholder",
                    modifier = Modifier
                        .size(100.dp)
                        .alpha(0.4f)
                )
            }
        }
    }
}

@Composable
fun DisplayHomeSlider(listMovies: List<Movie>) {
    Column {
        AutoSlidingCarousel(
            images = listMovies, modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(8.dp)
        )
    }
}

