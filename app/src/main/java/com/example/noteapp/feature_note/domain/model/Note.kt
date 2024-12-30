package com.example.noteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.ui.theme.BabyBlue
import com.example.noteapp.ui.theme.LightGreen
import com.example.noteapp.ui.theme.RedOrange
import com.example.noteapp.ui.theme.RedPink
import com.example.noteapp.ui.theme.Violet

@Entity(tableName = "notes")
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val attachments: String ,
    val userId: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
){
    companion object{
        val noteColors = listOf(RedOrange, RedPink, BabyBlue, Violet, LightGreen)
    }
}

class InvalidNoteException(message: String): Exception(message)
