package com.example.noteapp.feature_aunthentication.presentation.signUp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteapp.feature_aunthentication.presentation.AuthState
import com.example.noteapp.feature_aunthentication.presentation.AuthViewModel
import com.example.noteapp.feature_aunthentication.presentation.components.AppHeader
import com.example.noteapp.feature_aunthentication.presentation.components.BackgroundCircles
import com.example.noteapp.feature_aunthentication.presentation.components.SignUpForm
import com.example.noteapp.feature_note.utils.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteSignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel = koinViewModel<AuthViewModel>(),
) {
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> {
                navController.navigate(Screen.NotesScreen.route)
            }

            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    (authState.value as AuthState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
        }

    }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .verticalScroll(scrollState) // Make Column scrollable
    ) {
        BackgroundCircles()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {
            AppHeader()
            Spacer(modifier = Modifier.height(50.dp))
            SignUpForm(
                onSignUpClick = { username, email, password ->
                    authViewModel.signUp(username, email, password)
                }
            )

        }

    }
}

//
//@Preview
//@Composable
//fun NoteSignUpScreenPreview() {
//    NoteSignUpScreen(
//        navController = NavController(LocalContext.current),
//        authViewModel = AuthViewModel()
//    )
//}