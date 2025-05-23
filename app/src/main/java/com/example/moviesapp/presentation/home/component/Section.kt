package com.example.moviesapp.presentation.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
inline fun Section(title: String, content: @Composable () -> Unit) {
    Column {
        Text(text = title,
            style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xffffc107),),
            modifier = Modifier.padding(start = 16.dp, top = 30.dp)
        )

        Spacer(modifier = Modifier.height(22.dp))

        content()
    }
}