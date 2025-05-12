package com.example.moviesapp.presentation.intro

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moviesapp.R
import com.example.moviesapp.presentation.navigation.Screen

@Composable
fun IntroScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.blackBackground))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            HeaderSection()
            FooterSection(
                onClickGoStart = {
                    navController.navigate(Screen.ContentNavGraph.route) {
                        popUpTo(Screen.IntroScreen.route) { inclusive = true }
                    }
                }
            )
        }
    }
}

@Composable
fun FooterSection(onClickGoStart: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Button(
            onClick = { onClickGoStart() },
            modifier = Modifier
                .padding(top = 8.dp)
                .size(200.dp, 50.dp)
                .align(Alignment.TopCenter)
                ,
            shape = RoundedCornerShape(50.dp),
            border = BorderStroke(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(id = R.color.pink),
                        colorResource(id = R.color.green)
                    )
                )
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),

            ) {
            Text(
                text = "Go Start",
                fontSize = 18.sp,
                color = Color.White
            )
        }

    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(650.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier.matchParentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.woman2),
                contentDescription = null,
                modifier = Modifier.size(340.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Watching movies",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.cairo_bold))
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Download and watch your favorite movies on our app",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.cairo_regular))
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewIntroScreen(modifier: Modifier = Modifier) {
    IntroScreen(NavHostController(LocalContext.current))
}