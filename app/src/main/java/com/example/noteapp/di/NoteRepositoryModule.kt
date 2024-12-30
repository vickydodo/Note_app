package com.example.noteapp.di

import com.example.noteapp.feature_note.data.repo_impl.NoteRepositoryImpl
import com.example.noteapp.feature_note.domain.repo.NoteRepository
import org.koin.dsl.module

val noteRepositoryModule = module {
    single <NoteRepository>{ NoteRepositoryImpl(get()) }
}

