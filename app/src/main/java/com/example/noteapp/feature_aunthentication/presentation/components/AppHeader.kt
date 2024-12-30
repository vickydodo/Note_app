package com.example.noteapp.feature_aunthentication.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R


@Composable
fun AppHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_pushpin), // Replace with actual resource ID
            contentDescription = null,
            modifier = Modifier.size(170.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "NOTE-IT",
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun AppHeaderPreview() {
    AppHeader()
}