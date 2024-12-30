package com.example.noteapp.feature_note.domain.repo

import com.example.noteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteById(id: Int): Note?

    fun getNotesByUserId(userId: String): Flow<List<Note>>
}