package com.example.moviesapp.presentation.intro

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moviesapp.R
import com.example.moviesapp.presentation.navigation.Screen
import com.example.moviesapp.ui.theme.tertiaryDarkMediumContrast
import com.example.moviesapp.ui.theme.tertiaryLight
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    var progress by remember { mutableStateOf(0f) }
    val animationDuration = 3000 // 3 seconds
    val splashDuration = 3000L // 3 seconds should same for animationDuration

    val scale = remember { Animatable(0f) }
    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(3f).getInterpolation(it)
                })
        )
        progress = 1f

        delay(splashDuration)

        navController.navigate(Screen.IntroScreen.route) {
            popUpTo(Screen.SplashScreen.route) { inclusive = true }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.blackBackground))
    ) {

        Image(
            painter = painterResource(id = R.drawable.bg1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.alpha(0.5f)
        )

        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.align(Alignment.BottomCenter)
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .scale(scale.value)
                    .align(Alignment.CenterHorizontally) // Align horizontally to center
            )

            Spacer(modifier = Modifier.height(26.dp))

            AnimatedProgressBar(progress = progress, animationDuration = animationDuration)
        }

    }

}

@Composable
fun AnimatedProgressBar(progress: Float, animationDuration: Int) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = animationDuration)
    )

    LinearProgressIndicator(
        progress = { animatedProgress },
        color = colorResource(id = R.color.blue),//progress Color
        trackColor = tertiaryDarkMediumContrast,//
        modifier = Modifier
            .height(16.dp)
            .padding(horizontal = 4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
    )
}