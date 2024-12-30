package com.example.noteapp.feature_aunthentication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Top Circle Component
@Composable
fun BackgroundCircles() {
    Box(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .size(250.dp)
                .offset(x = 220.dp, y = 80.dp) // Positioned halfway out on the bottom-right
                .background(Color(0xFFD0D8FF), shape = CircleShape)
        )
        // Top-Left Circle
        Box(
            modifier = Modifier
                .size(180.dp)
                .offset(x = (-100).dp, y = (430).dp) // Positioned halfway out on the top-left
                .background(Color(0xFFD0D8FF), shape = CircleShape)
        )


    }
}

@Preview
@Composable
fun TopCirclePreview() {
    BackgroundCircles()
}