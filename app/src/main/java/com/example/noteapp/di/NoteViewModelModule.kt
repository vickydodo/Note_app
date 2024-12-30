package com.example.noteapp.di

import androidx.lifecycle.SavedStateHandle
import com.example.noteapp.feature_aunthentication.presentation.AuthViewModel
import com.example.noteapp.feature_note.presentation.add_edit_note.AddEditNoteViewModel
import com.example.noteapp.feature_note.presentation.notes.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val noteViewModelModule = module {

    viewModel {
        NoteViewModel(
            get(),
            get(),
            get()
        )
    }


    viewModel { (handle: SavedStateHandle) ->
        AddEditNoteViewModel(
            get(),
            get(),
            savedStateHandle = handle
        )
    }

    viewModel {
        AuthViewModel(
            get()
        )
    }


}

