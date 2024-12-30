package com.example.noteapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.feature_aunthentication.data.data_source.UserDao
import com.example.noteapp.feature_aunthentication.domain.model.User
import com.example.noteapp.feature_note.domain.model.Note

@Database(
    entities = [Note::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun userDao(): UserDao
}