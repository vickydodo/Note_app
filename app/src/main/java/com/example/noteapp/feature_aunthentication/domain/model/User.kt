package com.example.noteapp.feature_aunthentication.domain.model

data class User(
    val id: Int,
    val uid: String,
    val email: String,
    val name: String,
    val signUpTimestamp : Long = System.currentTimeMillis()
)
