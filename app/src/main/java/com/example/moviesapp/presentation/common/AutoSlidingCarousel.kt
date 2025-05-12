package com.example.moviesapp.presentation.common

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.utils.Constant.BASE_IMAGE_URL
import kotlinx.coroutines.delay


@Composable
fun AutoSlidingCarousel(
    images: List<Movie>,
    modifier: Modifier = Modifier,
    slideDuration: Long = 3000L,
) {
    var currentIndex by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(slideDuration)
            currentIndex = (currentIndex + 1) % images.size
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = modifier) {
            val imagePainter =
                rememberAsyncImagePainter(model = BASE_IMAGE_URL + images[currentIndex].poster_path)

//            Log.i("AutoSlidingCarousel", "imagePainter: $imagePainter")
            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))/*.pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        change.consume()
                        if (dragAmount > 0) {
                            currentIndex = (currentIndex - 1 + images.size) % images.size
                        } else {
                            currentIndex = (currentIndex + 1) % images.size
                        }
                    }
                }*/
            )

            // Gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 300f // Adjust as needed
                        )
                    )
            )

            // Title
            Text(
                text = images[currentIndex].title,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.netflixsans_regular, FontWeight.Normal)),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )


        }
        // Indicators
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
        ) {
            repeat(5) { index ->
                val size by animateDpAsState(targetValue = if (currentIndex % 5 == index) 12.dp else 8.dp)
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(size)
                        .clip(CircleShape)
                        .background(if (currentIndex % 5 == index) Color.White else Color.Gray)
                )
            }
        }


    }

}
