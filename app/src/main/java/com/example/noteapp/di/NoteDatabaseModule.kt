package com.example.noteapp.di

import androidx.room.Room
import com.example.noteapp.feature_aunthentication.data.data_source.UserDao
import com.example.noteapp.feature_note.data.data_source.NoteDao
import com.example.noteapp.feature_note.data.data_source.NoteDatabase
import org.koin.dsl.module

val noteDatabaseModule = module {

    single {
        Room.databaseBuilder(
            get(),
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    single<NoteDao>
    { get<NoteDatabase>().noteDao() }

    single<UserDao>
    { get<NoteDatabase>().userDao() }

}