package com.example.moviesapp.presentation.details.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.MovieCast
import com.example.moviesapp.presentation.details.MovieDetailsState
import com.example.moviesapp.utils.Constant.BASE_IMAGE_URL
import com.example.moviesapp.utils.UiState

@Composable
fun ContentMovieCast(uiState: MovieDetailsState) {
    when (val movieCastState = uiState.movieCastState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            movieCastState.data?.let { listMovieCast ->
                ListMovieCast(listMovieCast)
            }
        }

        is UiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .alpha(0.2f)
            ) {
                LazyRow(

                ) {
                    items(5){
                        Image(
                            painter = painterResource(R.drawable.user), // Replace with your placeholder
                            contentDescription = "Cast Member Placeholder",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(horizontal = 8.dp)
                                .clip(RoundedCornerShape(4.dp))
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ListMovieCast(listMovieCast: List<MovieCast>) {
    LazyRow(
        modifier = Modifier,
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        items(listMovieCast.size) { index ->
            MovieCastCard(listMovieCast[index])
        }
    }
}

@Composable
fun MovieCastCard(movieCast: MovieCast) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .width(120.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        if (movieCast.profile_path != null) {
            val context = LocalContext.current
            AsyncImage(
                model = ImageRequest.Builder(context).data(BASE_IMAGE_URL + movieCast.profile_path)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.BottomCenter),
                contentScale = ContentScale.Crop,
            )

        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.2f)
            ) {
                Image(
                    painter = painterResource(R.drawable.user), // Replace with your placeholder
                    contentDescription = "Cast Member Placeholder",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(4.dp))
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black), startY = 100f
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = movieCast.name, style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.cairo_regular)),
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )
            )
        }
    }
}
