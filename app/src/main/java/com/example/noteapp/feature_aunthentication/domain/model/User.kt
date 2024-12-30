package com.example.noteapp.feature_aunthentication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val uid: String,
    val email: String,
    val name: String,
    val signUpTimestamp : Long = System.currentTimeMillis()
)
