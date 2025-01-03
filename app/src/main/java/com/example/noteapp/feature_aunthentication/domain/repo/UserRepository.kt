package com.example.noteapp.feature_aunthentication.domain.repo

interface UserRepository {


    suspend fun signInWithGoogle(idToken: String)

}