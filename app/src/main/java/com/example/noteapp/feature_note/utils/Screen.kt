package com.example.noteapp.feature_note.utils

sealed class Screen(
    val route: String
) {
    data object WelcomeScreen: Screen("welcome_screen")
    data object LoginScreen: Screen("login_screen")
    data object SignUpScreen: Screen("sign_up_screen")
    data object NotesScreen: Screen("notes_screen")
    data object AddEditNoteScreen: Screen("add_edit_note_screen")
}