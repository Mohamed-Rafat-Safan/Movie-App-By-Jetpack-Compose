package com.example.moviesapp.presentation.details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.details.component.ContentMovieCast
import com.example.moviesapp.presentation.details.component.FavoriteButton
import com.example.moviesapp.utils.Constant.BASE_IMAGE_URL
import com.example.moviesapp.utils.UiState


@Composable
fun MovieDetailsScreen(
    movie: Movie,
    viewModel: MovieDetailsViewModel,
    onBackClick: () -> Unit,
) {


    LaunchedEffect(Unit) {
        viewModel.handleIntent(MovieDetailsIntent.CheckFavoriteMovie(movie))

        viewModel.handleIntent(MovieDetailsIntent.GetMovieCast(movie.id))
    }

    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.blackBackground)),
    ) {

        MovieDetailsContent(
            movie = movie,
            uiState = uiState,
            scrollState = scrollState,
            onBackClick = onBackClick,
            onFavoriteToggle = { isFavorite ->
                viewModel.handleIntent(
                    MovieDetailsIntent.ToggleFavorite(movie = movie, favorite = isFavorite)
                )
            }
        )

    }
}

@Composable
fun MovieDetailsContent(
    movie: Movie,
    uiState: MovieDetailsState,
    scrollState: ScrollState,
    onBackClick: () -> Unit,
    onFavoriteToggle: (Boolean) -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp, top = 40.dp)
                    .clickable { onBackClick() }
            )

            FavoriteButton(
                modifier = Modifier.align(Alignment.TopEnd),
                isFavorite = uiState.favoriteState,
                onClick = {
                    onFavoriteToggle(!uiState.favoriteState)
                    Toast.makeText(
                        context,
                        if (uiState.favoriteState) "Movie removed from favorites" else "Movie added to favorites",
                        Toast.LENGTH_SHORT
                    ).show()
                })


            AsyncImage(
                model = ImageRequest.Builder(context).data(BASE_IMAGE_URL + movie.backdrop_path)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.2f
            )

            AsyncImage(
                model = ImageRequest.Builder(context).data(BASE_IMAGE_URL + movie.poster_path)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp, 300.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .align(Alignment.BottomCenter),
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(R.color.black2),
                                colorResource(R.color.black1),
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY)
                        )
                    )
            )

        }


        MovieDetailsBody(movie = movie, uiState = uiState)

    }
}

@Composable
fun MovieDetailsBody(movie: Movie, uiState: MovieDetailsState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = movie.title,
            style = TextStyle(
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.cairo_regular))
            ),
            modifier = Modifier
                .padding(top = 10.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Release Date",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.cairo_bold))
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = movie.release_date,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.cairo_regular))
                    )
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Vote Count",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.cairo_bold))
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = movie.vote_count.toString(),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.cairo_regular))
                    )
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Rating",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.cairo_bold))
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.star),
                        contentDescription = null,
                        tint = colorResource(R.color.canary),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = movie.vote_average.toString(),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.cairo_regular))
                        )
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Overview",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.cairo_bold)),
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = movie.overview,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.netflixsans_regular)),
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        SectionMovieCast(uiState)

        Spacer(modifier = Modifier.height(32.dp))

    }
}

@Composable
fun SectionMovieCast(uiState: MovieDetailsState) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Cast",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.cairo_bold)),
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(6.dp))

        ContentMovieCast(uiState)

    }
}


