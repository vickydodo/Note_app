package com.example.noteapp.feature_aunthentication.domain.repo

import com.example.noteapp.feature_aunthentication.domain.model.User

interface UserRepository {

    suspend fun insertUser(user: User)

    suspend fun signInWithGoogle(idToken: String)

}