package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapp.feature_aunthentication.presentation.AuthViewModel
import com.example.noteapp.feature_aunthentication.presentation.signIn.NoteSignInScreen
import com.example.noteapp.feature_aunthentication.presentation.signUp.NoteSignUpScreen
import com.example.noteapp.feature_aunthentication.presentation.welcome.NoteWelcomeScreen
import com.example.noteapp.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.example.noteapp.feature_note.presentation.notes.NoteScreen
import com.example.noteapp.feature_note.utils.Screen
import com.example.noteapp.ui.theme.NoteAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {


    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = koinViewModel()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.WelcomeScreen.route
                    ) {
                        composable(route = Screen.WelcomeScreen.route) {
                            NoteWelcomeScreen(
                                navController = navController
                            )
                        }

                        composable(route = Screen.LoginScreen.route) {
                            NoteSignInScreen(
                                navController = navController
                            )
                        }

                        composable(route = Screen.SignUpScreen.route) {
                            NoteSignUpScreen(
                                navController = navController
                            )
                        }

                        composable(route = Screen.NotesScreen.route) {
                            val userId = authViewModel.userId.value
                            if (userId != null) {
                                NoteScreen(
                                    navController = navController
                                )
                            }
                        }

                        composable(route = Screen.AddEditNoteScreen.route +
                                "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }

                            )
                        ) {
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            val userId = authViewModel.userId.value
                            if (userId != null) {
                                AddEditNoteScreen(
                                    navController = navController,
                                    noteColor = color,
                                    userId = userId
                                )
                            }
                        }

                    }

                }
            }
        }
    }
}
