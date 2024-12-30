package com.example.noteapp.feature_note.utils

sealed class Screen(
    val route: String
) {
    object WelcomeScreen: Screen("welcome_screen")
    object LoginScreen: Screen("login_screen")
    object SignUpScreen: Screen("sign_up_screen")
    object NotesScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")
}