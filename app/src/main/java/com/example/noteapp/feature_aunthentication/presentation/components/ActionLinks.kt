package com.example.noteapp.feature_aunthentication.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActionLinks(
    onLoginClick: () -> Unit,
    onCreateAccountClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Log-in",
            style = MaterialTheme.typography.h4.copy(
                color = Color.Black,
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .clickable(
                    onClick = {
                        onLoginClick()
                    }

                )
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = AnnotatedString("Create a new account"),
            style = MaterialTheme.typography.h4.copy(
                color = Color.Black,
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .clickable(
                    onClick = {
                        onCreateAccountClick()
                    }
                )
        )
    }
}


@Preview
@Composable
fun ActionLinksPreview() {
    ActionLinks(
        onLoginClick = {},
        onCreateAccountClick = {}
    )
}