package com.example.noteapp.feature_aunthentication.presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteapp.feature_aunthentication.presentation.components.ActionLinks
import com.example.noteapp.feature_aunthentication.presentation.components.AppHeader
import com.example.noteapp.feature_aunthentication.presentation.components.BackgroundCircles
import com.example.noteapp.feature_aunthentication.presentation.components.Tagline
import com.example.noteapp.feature_note.utils.Screen

@Composable
fun NoteWelcomeScreen(
    navController: NavController,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        BackgroundCircles()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp)
        ) {
            AppHeader()
            Spacer(modifier = Modifier.height(8.dp))
            Tagline()
            Spacer(modifier = Modifier.height(250.dp))
            ActionLinks(onLoginClick = { navController.navigate(Screen.LoginScreen.route) },
                onCreateAccountClick = { navController.navigate(Screen.SignUpScreen.route) })

        }
    }

}

//@Preview
//@Composable
//fun NoteWelcomeScreenPreview() {
//    NoteWelcomeScreen(navController = NavController(LocalContext.current))
//}
