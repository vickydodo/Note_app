package com.example.noteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.feature_note.domain.model.Note
import com.example.noteapp.feature_note.domain.use_case.AddNoteUseCase
import com.example.noteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.example.noteapp.feature_note.domain.use_case.GetNoteByUserIdUseCase
import com.example.noteapp.feature_note.utils.NoteOrder
import com.example.noteapp.feature_note.utils.OrderType
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NoteViewModel(
    private val getNoteByUserId: GetNoteByUserIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: kotlinx.coroutines.Job? = null

    private var userId: String? = null

//    init {
//        getNotes(NoteOrder.Date(OrderType.Descending))
//    }

    // Method to set userId
    fun setUserId(userId: String) {
        this.userId = userId
        getNotes(NoteOrder.Date(OrderType.Descending))
    }


    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }

            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    addNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }

            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )

            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = userId?.let {
            getNoteByUserId(noteOrder, it).onEach {
                _state.value = state.value.copy(
                    notes = it,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
        }

    }

}