package com.example.noteapp.feature_note.domain.use_case

import com.example.noteapp.feature_note.domain.model.Note
import com.example.noteapp.feature_note.domain.repo.NoteRepository
import com.example.noteapp.feature_note.utils.NoteOrder
import com.example.noteapp.feature_note.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNoteByUserIdUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
        userId: String
    ): Flow<List<Note>> {
        return repository.getNotesByUserId(userId).map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                    }
                }
                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                    }
                }
            }
        }
    }
}