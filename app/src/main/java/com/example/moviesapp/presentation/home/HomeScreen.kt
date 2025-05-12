package com.example.moviesapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.common.SearchBar
import com.example.moviesapp.presentation.home.component.MovieSectionContent
import com.example.moviesapp.presentation.home.component.Section
import com.example.moviesapp.presentation.home.component.TrendingMovieSectionContent
import com.example.moviesapp.utils.Constant.DISCOVER
import com.example.moviesapp.utils.Constant.NOW_PLAYING
import com.example.moviesapp.utils.Constant.POPULAR
import com.example.moviesapp.utils.Constant.UPCOMING


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onClickSearchBar: () -> Unit,
    navigateToDetails: (Movie) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()


    // Trigger data loading
    LaunchedEffect(Unit) {
        viewModel.handleIntent(HomeIntent.LoadTrendingMovies)
        viewModel.handleIntent(HomeIntent.LoadPopularMovies)
        viewModel.handleIntent(HomeIntent.LoadTopRatedMovies)
        viewModel.handleIntent(HomeIntent.LoadUpcomingMovies)
        viewModel.handleIntent(HomeIntent.LoadDiscoverMovies)
        viewModel.handleIntent(HomeIntent.LoadNowPlayingMovies)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 46.dp, bottom = 60.dp)
    ) {
        Text(
            text = "What would you like to watch?",
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )

        SearchBar(
            isClickable = true,
            onclick = { onClickSearchBar() }
        )

        Spacer(modifier = Modifier.height(20.dp))


        TrendingMovieSectionContent(uiState.trendingMovies)


        Spacer(modifier = Modifier.height(8.dp))

        Section(title = "Popular Movies \uD83D\uDD25") {
            MovieSectionContent(POPULAR, uiState.popularMovies) { movie ->
                navigateToDetails(movie)
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        Section(title = "Upcoming Movies") {
            MovieSectionContent(UPCOMING, uiState.upcomingMovies)  { movie ->
                navigateToDetails(movie)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Section(title = "Discover Movies \uD83C\uDF1F") {
            MovieSectionContent(DISCOVER, uiState.discoverMovies)  { movie ->
                navigateToDetails(movie)
            }
        }


        Section(title = "Now Playing") {
            MovieSectionContent(NOW_PLAYING, uiState.nowPlayingMovies)  { movie ->
                navigateToDetails(movie)
            }
        }

    }

}


