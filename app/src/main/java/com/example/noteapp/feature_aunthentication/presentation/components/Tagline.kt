package com.example.noteapp.feature_aunthentication.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun Tagline() {
    Text(
        text = buildAnnotatedString {
            // First word: "Stay" (Black)
            append("Stay ")
            addStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal
                ),
                start = 0,
                end = 4
            )
            // Second word: "Centered" (Custom color)
            append("centered")
            addStyle(
                style = SpanStyle(
                    color = Color(0xFFD0D8FF), // Replace with your desired color
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal
                ),
                start = 5,
                end = 13
            )
        }
    )

}


@Preview
@Composable
fun TaglinePreview() {
    Tagline()
}