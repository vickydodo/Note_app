package com.example.noteapp.feature_aunthentication.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = if (enabled) Color(0xFFB0C4FF) else Color.Gray),
        modifier = Modifier
            .width(250.dp)
            .height(51.dp),
        border = BorderStroke(1.dp, Color(0xFFB0C4FF)),
        shape = RoundedCornerShape(40)
    )
    {
        Text(
            text = text, fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton(text = "Log-in", onClick = {}, enabled = false)
}