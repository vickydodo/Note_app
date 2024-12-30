package com.example.noteapp.feature_aunthentication.presentation.signIn

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteapp.R
import com.example.noteapp.feature_aunthentication.presentation.AuthState
import com.example.noteapp.feature_aunthentication.presentation.AuthViewModel
import com.example.noteapp.feature_aunthentication.presentation.components.AppHeader
import com.example.noteapp.feature_aunthentication.presentation.components.BackgroundCircles
import com.example.noteapp.feature_aunthentication.presentation.components.LogInForm
import com.example.noteapp.feature_aunthentication.presentation.components.SignInWithGoogleButton
import com.example.noteapp.feature_note.utils.Screen
import com.example.noteapp.ui.theme.Purple40

@Composable
fun NoteSignInScreen(
    navController: NavController,
    authViewModel: AuthViewModel ,
) {

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current


    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> {
                navController.navigate(Screen.NotesScreen.route) {
                    popUpTo(Screen.LoginScreen.route) { inclusive = true }
                }
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .verticalScroll(rememberScrollState()) // Make Column scrollable
    ) {
        BackgroundCircles()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
        ) {
            AppHeader()
            Spacer(modifier = Modifier.height(60.dp))
            LogInForm(
                onLoginClick = { email, password ->
                    authViewModel.logIn(email, password)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = stringResource(R.string.or), fontSize = 16.sp, color = Purple40)
            Spacer(modifier = Modifier.height(10.dp))
            SignInWithGoogleButton(){ credential ->
                authViewModel.onSignInWithGoogle(credential)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            TextButton(onClick = { navController.navigate(Screen.SignUpScreen.route) }) {
                Text(
                    text = stringResource(R.string.sign_up_description),
                    fontSize = 16.sp,
                    color = Purple40
                )
            }

        }

    }
}

//@Preview
//@Composable
//fun NoteSignInScreenPreview() {
//    NoteSignInScreen(
//        navController = rememberNavController(),
//        authViewModel = AuthViewModel{}
//    )
//}
