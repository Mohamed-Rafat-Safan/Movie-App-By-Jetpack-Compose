package com.example.moviesapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.utils.Constant.BASE_IMAGE_URL


@Composable
fun MovieCard(movie: Movie, onClick: (Movie) -> Unit) {
    // Your UI for a movie card
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(130.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(movie) }
            .background(
                color = Color(android.graphics.Color.parseColor("#2f2f39"))
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        // this like a glide image loader
        AsyncImage(
            model = ImageRequest.Builder(context).data(BASE_IMAGE_URL + movie.poster_path)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = movie.title,
            modifier = Modifier.padding(start = 4.dp),
            style = TextStyle(
                color = Color.White,
                fontSize = 12.sp,
            ),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xffffc107),
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = movie.vote_average.toString(),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp,
                ),
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

    }
}


@Composable
fun DiscoverMovieCard(movie: Movie, onClick: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(220.dp)
            .height(130.dp), onClick = {
            onClick(movie)
        }
    ) {
        Box( // Use Box for layering elements
            modifier = Modifier.fillMaxSize()
        ) {
            val context = LocalContext.current

            // this like a glide image loader
            AsyncImage(
                model = ImageRequest.Builder(context).data(BASE_IMAGE_URL + movie.poster_path)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )

            // Overlay with gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f)
                            )
                        )
                    )
            )

            // Title on bottom
            Text(
                text = movie.title,
                fontFamily = FontFamily(Font(R.font.cairo_regular)),
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                color = Color.White
            )
        }
    }
}