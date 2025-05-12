package com.example.moviesapp.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.presentation.navigation.MainNavGraph
import com.example.moviesapp.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // إعداد نافذة النظام
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // جعل الأيقونات بيضاء
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = false // false = أيقونات بيضاء

        setContent {
            MoviesAppTheme {
                MainNavGraph()
            }
        }
    }
}
