package com.example.moviesapp.presentation.common

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat


@Composable
fun HideNavigationBarOnly() {
    val context = LocalContext.current
    val view = LocalView.current
    val activity = context as ComponentActivity

    DisposableEffect(Unit) {
        val window = activity.window

        // Allow drawing behind system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val controller = ViewCompat.getWindowInsetsController(view)
        controller?.let {
            // Hide only the navigation bar
            it.hide(WindowInsetsCompat.Type.navigationBars())
            it.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        onDispose {
            // Show only the navigation bar again
            controller?.show(WindowInsetsCompat.Type.navigationBars())
        }
    }
}