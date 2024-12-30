package com.example.noteapp.di

import com.example.noteapp.feature_aunthentication.domain.use_case.AddUserUseCase
import com.example.noteapp.feature_note.domain.use_case.AddNoteUseCase
import com.example.noteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.example.noteapp.feature_note.domain.use_case.GetNoteByUserIdUseCase
import com.example.noteapp.feature_note.domain.use_case.GetNoteUseCase
import org.koin.dsl.module

val noteUseCaseModule = module {

    factory { GetNoteByUserIdUseCase(repository = get()) }
    factory { DeleteNoteUseCase(repository = get()) }
    factory { AddNoteUseCase(repository = get()) }
    factory { GetNoteUseCase(repository = get()) }
    factory { AddUserUseCase(userRepository = get()) }
}