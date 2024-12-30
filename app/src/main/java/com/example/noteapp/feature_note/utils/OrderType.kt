package com.example.noteapp.feature_note.utils

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}