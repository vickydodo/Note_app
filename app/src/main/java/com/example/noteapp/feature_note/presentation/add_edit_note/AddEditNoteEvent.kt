package com.example.noteapp.feature_note.presentation.add_edit_note

import android.net.Uri
import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnteredTitle(val value: String) : AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class EnteredContent(val value: String) : AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class ChangeColor(val color: Int): AddEditNoteEvent()
    data class AttachImage(val imageUri: Uri) : AddEditNoteEvent()
    data class AttachDocument(val fileUri: Uri) : AddEditNoteEvent()
    data class CaptureImage(val imageUri: Uri) : AddEditNoteEvent()


    object SaveNote : AddEditNoteEvent()
}
