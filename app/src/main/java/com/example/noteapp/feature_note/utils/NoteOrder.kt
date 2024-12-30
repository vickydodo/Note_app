package com.example.noteapp.feature_note.utils

sealed class NoteOrder( val orderType: OrderType) {
    class Date(  orderType: OrderType): NoteOrder(orderType)
    class Title( orderType: OrderType): NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {
        return when(this) {
            is Date -> Date(orderType)
            is Title -> Title(orderType)
        }
    }
}