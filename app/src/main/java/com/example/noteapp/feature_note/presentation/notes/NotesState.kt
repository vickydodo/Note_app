package com.example.noteapp.feature_note.presentation.notes

import com.example.noteapp.feature_note.domain.model.Note
import com.example.noteapp.feature_note.utils.NoteOrder
import com.example.noteapp.feature_note.utils.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
